package company.vk.education.androidcourse.rememberthepills.components.storage.models

import company.vk.education.androidcourse.rememberthepills.R

enum class MeasurementItem : TextedItem {
    Pieces {
        override val id: Int
            get() = 0

        override val textId: Int
            get() = R.string.drug_type_unit_pieces
    },
    Milligrams {
        override val id: Int
            get() = 1

        override val textId: Int
            get() = R.string.drug_type_unit_milligrams
    },
    Milliliters {
        override val id: Int
            get() = 2

        override val textId: Int
            get() = R.string.drug_type_unit_milliliters
    },
    Units {
        override val id: Int
            get() = 3

        override val textId: Int
            get() = R.string.drug_type_unit_units
    },
    Doses {
        override val id: Int
            get() = 4

        override val textId: Int
            get() = R.string.drug_type_unit_doses
    },
    Teaspoons {
        override val id: Int
            get() = 5

        override val textId: Int
            get() = R.string.drug_type_unit_teaspoons
    },
    Tablespoons {
        override val id: Int
            get() = 6

        override val textId: Int
            get() = R.string.drug_type_unit_tablespoons
    }
}