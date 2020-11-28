package main

import (
	"fmt"
	"net"
	"strings"
)

func main() {
	list, error := net.Listen("tcp", "127.0.0.1:8000")
	if error != nil {
		fmt.Println("listen error:", error)
	}

	defer list.Close()

	// 监听客户端连接请求
	for {
		fmt.Println("服务器等待接收请求")
		conn, er := list.Accept()
		if er != nil {
			fmt.Println("lst.Accept error", er)
		}
		// 具体完成服务器与客户端的通信
		go handlerConnect(conn)
		//buf := make([]byte, 4096)
		//m, error := conn.Read(buf)
		//if error != nil {
		//	fmt.Println("Read error:", error)
		//	return
		//}
		//fmt.Println("received data:", string(buf[:m]))
	}

}

func handlerConnect(conn net.Conn) {
	defer conn.Close()
	// 获取连接客户端Addr地址
	addr := conn.RemoteAddr()
	fmt.Println(addr.String(), "连接成功")
	buf := make([]byte, 4096)
	for {
		n, error := conn.Read(buf)
		// 如果n返回为0，则对端已经关闭套接字
		if error != nil {
			fmt.Println("Read error:", error)
			return
		}
		conn.Write([]byte(strings.ToUpper(string(buf[:n]))))
		fmt.Println("服务器督导数据：", string(buf[:n]))
	}

}
