package main

import (
	"fmt"
	"net/http"
)

func handle(w http.ResponseWriter, r *http.Request) {
	fmt.Println("-->", r.URL)
}

func main() {
	http.HandleFunc("/", handle)
	http.ListenAndServe("8080", nil)
}
