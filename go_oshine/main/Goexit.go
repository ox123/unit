package main

import (
	"fmt"
	"runtime"
)

func test1() {
	defer  fmt.Println("cccc")
	runtime.Goexit()
	fmt.Println("dddddd")
}

func main() {
	fmt.Println(runtime.GOARCH)
	fmt.Println(runtime.Version())
	go func() {
		defer  fmt.Println("aaaaaaaaaaaaaaaaaa")
		test1()
		fmt.Println("bbbbbbbbbbbbbbbbbb")
	}()
	fmt.Println("I am the main")
	for  {
		;
	}
}