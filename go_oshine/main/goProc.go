package main

import (
	"fmt"
	"runtime"
)

func main() {
	fmt.Println(runtime.GOROOT())
	runtime.GOMAXPROCS(4)
	fmt.Println(runtime.NumCPU())
	fmt.Print(runtime.NumGoroutine())
	//for{
	//	go  fmt.Print(0)
	//	fmt.Print(1)
	//}
}