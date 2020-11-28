package main

import (
	"fmt"
	_ "log"
	"math"
	"os"
	_ "strings"
	_ "sync"
)

var X int64
var Y int64

/*
This is a
*/
func main() {
	abc := func(x float64) float64 {
		return math.Sqrt(x)
	}
	fmt.Println("----------------", abc(9))
	fmt.Println(os.Getenv("user.home"))
	fmt.Println(os.Getenv("USER"))
	fmt.Println(os.Getenv("GOROOT"))
	var x  = 1
	var y  = 2
	swap(&x,&y)
	fmt.Print(x,y)
	//fmt.Println(time.Now())
	//s := "Hello 世界"
	//b := []byte(s)
	//b[5] = ','
	//fmt.Printf("%s", s)
	//fmt.Printf("%s", b)
	//fmt.Print(len("hello world"))
	//fmt.Println(strings.HasPrefix(s, "H"))
}

func swap(x *int, y * int) {
	var temp int
	temp= *x
	*x = *y
	*y = temp;
}
func test() {

	var str1 = "this is a  word"
	var str2 = `this is the second \n word`
	//for i := 0; i < len(str1); i++ {
	//	fmt.Println(str1[i])
	//}
	fmt.Println("===========")
	for _, v := range str1 {
		fmt.Printf("%c", v)
	}
	fmt.Println("====end=======")
	fmt.Println(str1[0:5])
	fmt.Println(str2)
	X, Y = 2, 4
	if X == Y {
		fmt.Println("===========")
	}
	x, y := 4, 4
	if x == y {
		fmt.Println("x====y")
	}
	var bb rune = 123456
	fmt.Println(bb)
	fmt.Println("-----start-----")
	//fmt.Println(reflect.TypeOf(X))
	var (
		HOME   = os.Getenv("HOME")
		USER   = os.Getenv("USER")
		GOROOT = os.Getenv("GOROOT")
	)
	fmt.Println(HOME, USER, GOROOT)
	fmt.Println("adsafdsf")
	fmt.Println("-----")
	var a = "10"
	fmt.Println(a)
	fmt.Println(os.Getenv("USER"))
	//const name int=  10
	//var  a *int
	//b := &a
	//fmt.Print(b)
	//c  :=  20
	//ss := "hello"
	//const PI float32 = 3.1415
	//var dd string = "aa"
	//fmt.Print(dd)
	//const Name  = true
	//fmt.Print(ss)
	//fmt.Println(c)
	fmt.Print(os.Getegid())
	//fmt.Print(os.Getenv("HOME"))

	var (
		aaa int
		bbb string
	)
	ac := 10
	fmt.Println(ac)
	//ac := 100
	//fmt.Println(ac)
	fmt.Print(aaa)
	fmt.Print(bbb)
	//os.
	//var(
	//	a int
	//	c bool
	//)
	//a=5
	//c=10
	//const name  = iota
	//io.Reader()
	//var str string = "safd"
	//var matcher = make(map[string] )
	const (
		connected   = 0
		disconneted = -1
		cc          = iota
		dd
		ee
		d, e, f = iota, iota, iota
	)
	fmt.Println(connected)
	fmt.Println(disconneted)
	//const z = iota
	fmt.Println("****----", cc)
	fmt.Println(dd)
	fmt.Println(ee)
	fmt.Println(d)
	fmt.Println(e)
	fmt.Println(f)
	fmt.Println(os.Getgid())
	fmt.Println(os.Getwd())
	fmt.Println(os.Getpagesize())
	ccc := "sdafdsaf"
	fmt.Println(len(ccc))
}

func init() {
	main()
	fmt.Println("-----init function-------")
	c := 200
	c <<= 2
	fmt.Println(c)
}
