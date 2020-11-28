package main

import (
	"fmt"
)

type PersonInfo struct {
	ID      string
	Name    string
	Address string
}

func getSequnce() func() int {
	i := 0
	return func() int {
		i++
		return i
	}
}

func main() {
	var arr [10] int = [10] int{1, 2, 3, 4, 5}
	for _, item := range arr {
		fmt.Println(item)
	}
	fmt.Println("=============")
	var sub []int = arr[:5]
	for _, item := range sub {
		fmt.Println(item)
	}
	fmt.Println("------------")
	mySlice := make([]int, 5, 10)
	fmt.Println(len(mySlice))
	fmt.Println("----->>>>", cap(mySlice))
	for _, i := range mySlice {
		fmt.Println(i)
	}
	var personDB map[string]PersonInfo = make(map[string]PersonInfo, 100)
	personDB["1233"] = PersonInfo{"123", "tom", "room"}
	p, ok := personDB["1233"]
	if ok {
		fmt.Println(p.Name)
	} else {
		fmt.Print("nothing")
	}
	num :=  2
	switch {
	case 0 <= num && num <= 3:
		fmt.Print("0-3")
	}
	//fmt.Print(cap(personDB))
	//str := "Hello, world"
	//fmt.Printf("aaaa",len(str))
	//for i,ch := range str {
	//	fmt.Println(i,ch)
	//}
	//nextNumber := getSequnce()
	//fmt.Println(nextNumber())
	//fmt.Println(nextNumber())
	//fmt.Println(nextNumber())
	//nextNumber1 := getSequnce()
	//fmt.Println(nextNumber1())
	//fmt.Println(nextNumber1())
	//fmt.Println(nextNumber1())
	//file, err := os.Open("test1.go")
	//var b  = make([]byte,1024)
	//file.Read(b)
	//fmt.Println(err)
	//fmt.Println(b)

	//a := 0
	//b := 5
	//for a<b {
	//	a ++
	//	fmt.Println("a==%d\n",a)
	//}
}
