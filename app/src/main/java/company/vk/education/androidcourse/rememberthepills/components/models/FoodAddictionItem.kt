package company.vk.education.androidcourse.rememberthepills.components.models

import company.vk.education.androidcourse.rememberthepills.R

enum class FoodAddictionItem : TextedItem {
    BeforeMeal {
        override val id: Int
            get() = 0

        override val textId: Int
            get() = R.string.food_addiction_before_meals
    },
    AfterMeal {
        override val id: Int
            get() = 1

        override val textId: Int
            get() = R.string.food_addiction_after_meal
    },
    WhileEating {
        override val id: Int
            get() = 2

        override val textId: Int
            get() = R.string.food_addiction_while_eating
    },
    NotDepend {
        override val id: Int
            get() = 3

        override val textId: Int
            get() = R.string.food_addiction_not_depend
    };
}