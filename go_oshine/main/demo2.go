package main

import (
	"flag"
	"fmt"
)

type AnimalCategory struct {
	kingdom string // 界。
	phylum  string // 门。
	class   string // 纲。
	order   string // 目。
	family  string // 科。
	genus   string // 属。
	species string // 种。
}

type Animal struct {
	scientificName string // 学名。
	AnimalCategory    // 动物基本分类。
}

func (ac AnimalCategory) String() string {
	return fmt.Sprintf("%s%s%s%s%s%s%s",
		ac.kingdom, ac.phylum, ac.class, ac.order,
		ac.family, ac.genus, ac.species)
}

func (a Animal) Category() string {
	return a.AnimalCategory.String()
}

func main() {
	//var name string
	//flag.StringVar(&name, "name", "everyone", "The greeting object.")
	//var c = *flag.String("name","cc","The last")
	var name = *flag.String("name", "everyone", "The greeting object.")
	flag.Parse()
	fmt.Println("Hello,%v!\n", name)
	cat := AnimalCategory{species: "cat"}
	fmt.Printf("%s\n", cat)
	animal := Animal{
		scientificName: "American Shorthair",
		AnimalCategory: category,
	}
	fmt.Printf("%s",animal)

}
