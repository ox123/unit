package main

import (
	"bufio"
	"fmt"
	"io"
	"os"
	"strings"
	"time"
)

func main() {
	fmt.Print(time.Now().UnixNano())
	path := "d:/tmp/aa.data"
	//f, error := os.Create(path)
	//if error !=nil {
	//	fmt.Println("xxxxxx")
	//	return
	//}
	//
	//defer f.Close()

	f, error := os.OpenFile(path, os.O_RDWR, 6)
	//n, e := f.WriteString("ssss\n\r")
	if error != nil {
		fmt.Println("out of service")
		return
	}
	// c创建一个带有缓冲区的reader
	reader := bufio.NewReader(f)
	for {
		readByte, error := reader.ReadBytes('\n')
		if error != nil && error == io.EOF {
			fmt.Print("readByte:", error)
			return
		}else{

		}
		fmt.Println(string(readByte))
	}


	//off, error := f.Seek(-2, io.SeekEnd)
	//// 指定在文件偏移位置，写入[]byte，通常搭配Seek()
	//f.WriteAt([]byte("abc"), off)
	//if e != nil {
	//	fmt.Println("xxx")
	//	return
	//}
	//fmt.Print("-->", n)
	str := "I love my work"
	ret := strings.Split(str,"I")
	for _, k := range ret{
		fmt.Print(k)
	}
	//ss := strings.Fields(str)
	//for _,va := range ss{
	//	fmt.Println(va)
	//}
	//fmt.Println(strings.HasPrefix(str,"I"))
	//fmt.Println(strings.HasPrefix(str,"work"))
}
