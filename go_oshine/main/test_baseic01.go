package main

import (
	"context"
	"fmt"
)

func Hello() {
	fmt.Println("hello everybody , I'm asong")
}
func main() {
	go Hello()
	fmt.Println("hello")
	//context.WithValue()
	context.Background()
	//context.WithValue()
}