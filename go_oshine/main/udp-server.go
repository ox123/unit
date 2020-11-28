package main

import (
	"fmt"
	"net"
)

func main() {
	laddr, err := net.ResolveUDPAddr("udp", "localhost:8008")
	if err != nil {
		fmt.Println("resolved udp addr:", err)
	}
	conn, error := net.ListenUDP("udp", laddr)
	if error != nil {
		fmt.Println("ListenUDP error:", error)
	}
	defer conn.Close()
	buf := make([]byte, 4096)
	// 返回三个值，分别读取到的字节数，客户端的地址，错误信息
	n, caddr, error := conn.ReadFromUDP(buf)
	if err!=nil {
		fmt.Println(error)
	}
	fmt.Println(caddr)
	fmt.Println(buf[:n])
}
