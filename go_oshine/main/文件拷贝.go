package main

import (
	"fmt"
	"io"
	"os"
)

func main() {
	path := "d:/tmp/aa.data"
	f_r, e := os.Open(path)
	if e != nil {
		fmt.Print("xxx")
		return
	}
	defer f_r.Close()
	f_w, e := os.Create("d:/tmp/aa_dest.data")
	if e != nil {
		fmt.Print("xxx")
		return
	}
	defer f_w.Close()
	buf := make([]byte, 4096)
	for {
		n, e := f_r.Read(buf)
		if e != nil && e == io.EOF {
			fmt.Printf("read finished, n:%d", n)
			return
		}
		f_w.Write(buf[:n]) // 读多少，就写多少
	}

}
