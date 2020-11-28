package main

import (
	"fmt"
)
import (
	"C"
)

var (
	a int
	b bool
)
//
//type PersonInfo struct {
//	ID      string
//	Name    string
//	Address string
//}

func maptest() {

	var personDB map[string]PersonInfo
	personDB = make(map[string]PersonInfo)
	personDB["123"] = PersonInfo{"123", "Tom", "rom"}
	value, ok := personDB["1234"]
	if ok {
		fmt.Println(value)
	} else {
		fmt.Println("no value found")
	}
	fmt.Println("----map end-----")
}

//func swap(a int, b int) (int, int) {
//	return b, a
//}

func min(a int, b int) int {
	var max = a
	if a < b {
		max = b
	} else {
		max = a
	}
	return max
}

func main() {
	var aaa = 10
	var bbbb = 20
	//aaa, bbbb = swap(aaa, bbbb)
	fmt.Println(aaa, bbbb)
	acc := 80
	for acc < 90 {
		fmt.Println(acc)
		acc++
	}
	fmt.Println(acc)
	mark := "90"
	switch {
	case mark == "90":
		fmt.Println("excellent")

	}
	maptest()
	fmt.Print("aaaa")
	const (
		a = iota
		b
		c = "ha"
		d
		e = iota
	)
	const (
		aa = "aa"
		dd
	)
	str := "hello"
	fmt.Println(len(str), str[0], str[len(str)-1])
	n := len(str)
	for i := 0; i < n; i++ {
		ch := str[i]
		fmt.Println(i, ch)
	}
	for i, ch := range str {
		fmt.Print(i, ch)
	}
	fmt.Println(a, b, c, d, e, aa, dd)
	slice()
}

func slice() {
	fmt.Println("--------slice start-----------")
	arr := [5]int{1, 2, 3, 4, 5}
	modify(arr)
	fmt.Println(arr)

	var mySlice = arr[:3]
	for i, ch := range mySlice {
		fmt.Println(i, ch)
	}

	s1 := make([]int, 5, 10)
	for i, ch := range s1 {
		fmt.Println(i, ch)
	}
	fmt.Println(cap(s1))
	fmt.Println(len(s1))
	mySlice1 := []int{8, 9, 10}
	ints := append(s1, mySlice1...)
	//copy
	fmt.Println(ints)
	fmt.Println("--------slice end-----------")
	fmt.Println("--copy begin----")
	slice1 := []int{1, 2, 3, 4, 5}
	slice2 := []int{5, 4, 9}
	copy(slice1, slice2)
	for i, v := range slice1 {
		fmt.Println(i, v)
	}
	fmt.Println("--copy end----")
}

func modify(array [5]int) {
	array[0] = 10
	fmt.Println(array)
}
