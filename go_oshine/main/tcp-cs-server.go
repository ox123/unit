package main

import (
	"fmt"
	"net"
)

func main() {
	// 创建一个用于监听的套接字
	lst, error := net.Listen("tcp", "127.0.0.1:8000")
	if error != nil {
		fmt.Println("net.listner error:", error)
	}
	// 阻塞客户端监听客户端连接请求
	conn, er := lst.Accept()
	if er != nil {
		fmt.Println("lst.Accept error", er)
	}
	buf := make([]byte, 4096)
	m, error := conn.Read(buf)
	if error != nil {
		fmt.Println("Read error:", error)
		return
	}
	fmt.Println("received data:", string(buf[:m]))
	conn.Write(buf[:m])
	defer lst.Close()
	defer conn.Close()
}
