package main
import (
	"fmt"
	"sync"
	"sync/atomic"
)

func main() {
	// 账户初始值为 0 元
	var balance int32
	balance = int32(0)
	done := make(chan bool)
	// 执行 10000 次转账，每次转入 1 元
	count := 10000

	var lock sync.Mutex

	for i := 0; i < count; i++ {
		// 这里模拟异步并发转账
		go transfer(&balance, 1, done, &lock)
	}
	// 等待所有转账都完成
	for i := 0; i < count; i++ {
		<-done
	}
	// 打印账户余额
	fmt.Printf("balance = %d \n", balance)
}
// 转账服务
func transfer(balance *int32, amount int, done chan bool, lock *sync.Mutex) {
	lock.Lock()
	*balance = *balance + int32(amount)
	lock.Unlock()
	done <- true
}


func transferCas(balance *int32, amount int, done chan bool) {
	for {
		old := atomic.LoadInt32(balance)
		new := old + int32(amount)
		if atomic.CompareAndSwapInt32(balance, old, new) {
			break
		}
	}
	done <- true
}

func transferFaa(balance *int32, amount int, done chan bool) {
	atomic.AddInt32(balance, int32(amount))
	done <- true
}