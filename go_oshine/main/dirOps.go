package main

import (
	"fmt"
	"time"
)

func sing() {
	for i := 0; i < 5; i++ {
		fmt.Println("-----singing------")
		time.Sleep(100 * time.Microsecond)
	}
}

func dance() {
	for i := 0; i < 5; i++ {
		fmt.Println("-----dancing------>>>>")
		time.Sleep(100 * time.Microsecond)
	}

}

func main() {
	go  func(){
		for i := 0; i < 5; i++ {
			fmt.Println("---i am groutine---")
			time.Sleep(time.Second)
		}
	}()

	for i := 0;i<5 ;i++  {
		fmt.Println("---i am main---")
		if i == 2 {
			break
		}
		time.Sleep(time.Second)
	}
	//go  sing()
	//go  dance()
	//for{
	//	;
	//}
	//fmt.Println("please input the path")
	//var path string
	//fmt.Scan(&path)
	//file, _ := os.OpenFile(path, os.O_RDONLY, os.ModeDir)
	//defer file.Close()
	//
	//infos, _ := file.Readdir(-1)
	//for _,info :=  range infos{
	//	if info.IsDir() {
	//		fmt.Printf("it is dir :%s",info.Name())
	//	}
	//	fmt.Println()
	//}
}
