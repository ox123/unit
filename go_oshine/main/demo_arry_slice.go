package main

import (
	"errors"
	"fmt"
)

func main() {
	s1 := make([]int, 5)
	fmt.Println(len(s1))
	fmt.Println(cap(s1))
	s2 := make([]int, 5, 8)
	fmt.Println(len(s2))
	fmt.Println(cap(s2))
	err := errors.New("undefined")
	fmt.Println(err)
}
