package main

import (
	"fmt"
	"time"
)

func main() {
	ch := make(chan int)
	go func() {
		for i := 0; i < 5; i++ {
			fmt.Println("i=", i)
			ch <- i
		}
	}()
	time.Sleep(time.Second * 2)
	for i := 0; i < 5; i++ {
		str := <-ch
		fmt.Println("主go程--》", str)
	}
	for {
		;
	}
}
