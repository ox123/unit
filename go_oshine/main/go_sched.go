package main

import (
	"fmt"
	"runtime"
)

func main() {
	go func() {
		for {
			fmt.Println("----this is goroutine test-----")
			//time.Sleep(100* time.Second.Seconds())
		}
	}()

	for   {
		runtime.Gosched() // 出让当前cpu时间片
		fmt.Println("I am main")
	}
	fmt.Errorf("cc")
}