package com.capybara.intuition.game.data.mapper


/*
fun ResourceEntity.toDescriptor(
notebookId: String
): ResourceDescriptor {


return when (type) {

    ResourceType.COLOR -> {

        ColorDescriptor(

            notebookId = notebookId,
            resourceId = name,
            color = Color(
                    value
                        .removePrefix("#")
                        .toULong(16)
                            or 0xFF000000UL
                ).value
        )
    }

    ResourceType.DRAWABLE -> {
        DrawableDescriptor(
            notebookId = notebookId,
            resourceId = name,
            assetPath = value
        )
    }
}


}

 */