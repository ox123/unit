package main

import (
	"fmt"
	"os"
)

func main() {
	list := os.Args
	fileName := list[1]
	fileInfo, err := os.Stat(fileName)
	if err != nil {
		fmt.Println("--->", err)
		return
	}
	fmt.Println(fileInfo.Name())
	fmt.Println(fileInfo.IsDir())
	fmt.Println(fileInfo.ModTime())
	fmt.Println(fileInfo.Size())
}
