package main

import (
	"fmt"
	"sync/atomic"
	"time"
)

func main() {
	var m atomic.Value
	m.Load()
	fmt.Println("now:",time.Now())
	myTicker := time.NewTicker(time.Second *2)
	go func() {
		for  {
			nowTime := <- myTicker.C
			fmt.Println("-->",nowTime)
		}
	}()
	//atomic.Value{aaa}
	for  {
		;
	}
	// 第一种方法
	//time.Sleep(time.Second)
	//
	//// 第二种方法
	//fmt.Println(time.Now())
	//mytimer := time.NewTimer(time.Second * 2)
	////fmt.Println(<-mytimer.C)
	//go func() {
	//	<- mytimer.C
	//	fmt.Print("child go finish")
	//}()
	//// 第三种
	//nowTime := <-time.After(time.Second *2)
	//fmt.Print(nowTime)
	time.After(time.Second)
}
