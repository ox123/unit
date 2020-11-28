package main

import (
	"fmt"
	"net"
)

type Client struct {
	C    chan string
	Name string
	Addr string
}

// 保存在线用户信息
var onlineMap map[string]Client
// 创建全局channel
var message = make(chan string)

func Manager() {
	onlineMap = make(map[string]Client)
	for {
		// 有数据则写入msg，无数据则阻塞在此处。
		msg := <-message
		for _, clnt := range onlineMap {
			clnt.C <- msg
		}
	}
}

func MakeMsg(clnt Client, msg string) (buf string) {
	buf = "[" + clnt.Addr + "]" + "\t" + clnt.Name + "\t" + msg
	return
}

func HandlConnect(conn net.Conn) {
	netAddr := conn.RemoteAddr().String()
	clnt := Client{make(chan string), netAddr, netAddr}
	onlineMap[netAddr] = clnt
	go writeMsgToClient(clnt, conn)
	message <- MakeMsg(clnt, "login")
	defer conn.Close()
	go func() {
		buf := make([]byte,4096)
		n, err := conn.Read(buf)
		if n ==0 {
			fmt.Println("user offline")
			return
		}
		if err != nil {
			return
		}
		msg := string(buf[:n-1])
		if msg == "who" && len(msg) == 3 {
			for _,user := range onlineMap{
				useInfo := user.Addr +":"+user.Name
				conn.Write([]byte(useInfo))
			}
		}
		message <- MakeMsg(clnt,msg)
	}()
	for {
		;
	}
}

func writeMsgToClient(client Client, conn net.Conn) {
	for msg := range client.C {
		conn.Write([]byte(msg + "\n"))
	}
}

func main() {
	listner, err := net.Listen("tcp", "127.0.0.1:8000")
	if err != nil {
		fmt.Print("listen error", err)
		return
	}
	defer listner.Close()
	// 创建管理者资源，map与全局资源
	go Manager()

	for {
		conn, error := listner.Accept()
		if error != nil {
			fmt.Print("Accept error", err)
			return
		}
		go HandlConnect(conn)
	}
}
