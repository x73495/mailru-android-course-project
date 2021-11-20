package company.vk.education.androidcourse.rememberthepills.components.storage.models

import company.vk.education.androidcourse.rememberthepills.R

enum class DrugTypeItem : TextedItem {
    Tablet {
        override val id: Int
            get() = 0

        override val textId: Int
            get() = R.string.drug_type_tablet
    },
    Inhaler {
        override val id: Int
            get() = 1

        override val textId: Int
            get() = R.string.drug_type_inhaler
    },
    Spray {
        override val id: Int
            get() = 2

        override val textId: Int
            get() = R.string.drug_type_spray
    },
    Injection {
        override val id: Int
            get() = 3

        override val textId: Int
            get() = R.string.drug_type_injection
    },
    Drops {
        override val id: Int
            get() = 4

        override val textId: Int
            get() = R.string.drug_type_drops
    },
    Ointment {
        override val id: Int
            get() = 5

        override val textId: Int
            get() = R.string.drug_type_ointment
    },
    Powder {
        override val id: Int
            get() = 6

        override val textId: Int
            get() = R.string.drug_type_powder
    },
    Suppository {
        override val id: Int
            get() = 7

        override val textId: Int
            get() = R.string.drug_type_suppository
    },
    Potion {
        override val id: Int
            get() = 8

        override val textId: Int
            get() = R.string.drug_type_potion
    };
}