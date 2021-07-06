package main

import (
	"fmt"
	"net/http"
)

func handler(w http.ResponseWriter, r *http.Request) {
	// w：写回给客户端（浏览器）的数据
	// r: 从 客户端 浏览器 读到的数据
	fmt.Println("Header:", r.Header)
	fmt.Println("URL:", r.URL)
	fmt.Println("Method:", r.Method)
	fmt.Println("Host:", r.Host)
	fmt.Println("RemoteAddr:", r.RemoteAddr)
	fmt.Println("Body:", r.Body)
	w.Write([]byte("hello 9fud9fd9"))
}

func main()  {
	// 注册回调函数。 该回调函数会在服务器被访问时，自动被调用。
	http.HandleFunc("/", handler)

	// 绑定服务器监听地址
	http.ListenAndServe("0.0.0.0:8000", nil)
}
