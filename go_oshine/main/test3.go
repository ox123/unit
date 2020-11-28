package main

import (
	"fmt"
)

func main()  {
	fmt.Print("----")
	kvs := map[string]string{"aaaa":"aaa"}
	for k,v := range kvs{
		fmt.Println(k,v)
	}
	for item := range kvs{
		fmt.Println(item)
	}
	for i,c := range "go"{
		fmt.Println(i,c)
	}
}