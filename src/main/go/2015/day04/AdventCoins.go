package main

import (
	"crypto/md5"
	"encoding/hex"
	"fmt"
	"strconv"
	"strings"
)

func main() {
	hash := md5.New()
	example := "Hello GO"
	hash.Write([]byte(example))
	fmt.Printf("hash of %s: %s\n", example, hex.EncodeToString(hash.Sum(nil)))

	calculate("abcdef")
	calculate("pqrstuv")
	calculate("yzbqklnj")
}

func calculate(input string) {
	for i := 0; ; i++ {
		if isGoodHash(input, i) {
			fmt.Printf("lowest key for %s: %d\n", input, i)
			return
		}
	}
}

func isGoodHash(input string, suffix int) bool {
	hash := md5.New()
	hash.Write([]byte(input + strconv.Itoa(suffix)))
	if strings.HasPrefix(hex.EncodeToString(hash.Sum(nil)), "000000") {
		return true
	}
	return false
}
