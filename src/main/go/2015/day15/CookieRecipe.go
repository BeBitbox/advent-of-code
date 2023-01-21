package main

import "fmt"

func main() {
	ingredients := make([]Ingredient, 0)
	ingredients = append(ingredients,
		Ingredient{
			name:       "Sprinkles",
			capacity:   2,
			durability: 0,
			flavor:     -2,
			texture:    0,
			calories:   3,
		},
		Ingredient{
			name:       "Butterscotch",
			capacity:   0,
			durability: 5,
			flavor:     -3,
			texture:    0,
			calories:   3,
		},
		Ingredient{
			name:       "Chocolate",
			capacity:   0,
			durability: 0,
			flavor:     5,
			texture:    -1,
			calories:   8,
		},
		Ingredient{
			name:       "Candy",
			capacity:   0,
			durability: -1,
			flavor:     0,
			texture:    5,
			calories:   8,
		},
	)

	maxScore := 0
	maxScoreCalories := 0
	combination := "NOT ONE"
	combinationCalories := "NOT ONE"
	for i := 0; i <= 100; i++ {
		for j := 0; j <= 100-i; j++ {
			for g := 0; g <= 100-i-j; g++ {
				h := 100 - i - j - g

				capacity := ingredients[0].capacity*i + ingredients[1].capacity*j + ingredients[2].capacity*g + ingredients[3].capacity*h
				durability := ingredients[0].durability*i + ingredients[1].durability*j + ingredients[2].durability*g + ingredients[3].durability*h
				flavor := ingredients[0].flavor*i + ingredients[1].flavor*j + ingredients[2].flavor*g + ingredients[3].flavor*h
				texture := ingredients[0].texture*i + ingredients[1].texture*j + ingredients[2].texture*g + ingredients[3].texture*h
				calories := ingredients[0].calories*i + ingredients[1].calories*j + ingredients[2].calories*g + ingredients[3].calories*h
				if capacity < 0 {
					capacity = 0
				}
				if durability < 0 {
					durability = 0
				}
				if flavor < 0 {
					flavor = 0
				}
				if texture < 0 {
					texture = 0
				}
				if calories < 0 {
					calories = 0
				}
				score := capacity * durability * flavor * texture
				if score > maxScore {
					maxScore = score
					combination = fmt.Sprint("Best Cookie %d/%d/%d/%d  \n ", i, j, g, h)
				}
				if calories == 500 && score > maxScoreCalories {
					maxScoreCalories = score
					combinationCalories = fmt.Sprint("500 calorie Cookie %d/%d/%d/%d  \n ", i, j, g, h)
				}
			}
		}
	}

	fmt.Printf("Winner %s with %d\n", combination, maxScore)
	fmt.Printf("best 500 calorie cookie %s with %d", combinationCalories, maxScoreCalories)
}

type Ingredient struct {
	name       string
	capacity   int
	durability int
	flavor     int
	texture    int
	calories   int
}
