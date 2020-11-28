package main

import (
	"fmt"
	"net"
)

func main() {
	// 根据ip与端口号创建通信套接字
	conn, error := net.Dial("tcp", "127.0.0.1:8000")
	if error != nil {
		fmt.Println("dial error:", error)
	}
	defer conn.Close()

	// 主动写数据给服务器
	conn.Write([]byte("Are you ok?"))
	if error != nil {
		fmt.Print("dd",error)
	}

	buf := make([]byte, 4096)
	m, error := conn.Read(buf)
	if error != nil {
		fmt.Println("Read error:", error)
		return
	}
	fmt.Println("client received data:", string(buf[:m]))
}
