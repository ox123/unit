package main

import (
	"fmt"
	"time"
)

func producer(out chan<- int) {
	for i := 0; i < 10; i++ {
		out <- i * i
	}
	close(out)
}

func consumer(in <-chan int) {
	for num := range in {
		fmt.Println(num)
	}

}
func main() {
	fmt.Print(time.Now().UnixNano())
	ch := make(chan int)
	go producer(ch)
	consumer(ch)
}
