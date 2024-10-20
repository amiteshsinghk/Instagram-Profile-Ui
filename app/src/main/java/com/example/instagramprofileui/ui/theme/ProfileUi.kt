package com.example.instagramprofileui.ui.theme

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.instagramprofileui.R
import com.example.instagramprofileui.entity.BottomMenuEntity
import com.example.instagramprofileui.entity.PostEntity
import com.example.instagramprofileui.entity.TabEntity

@Composable
fun ProfileScreen() {
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(top = 50.dp, start = 16.dp, end = 16.dp)
        ) {
            TopBar(userId = "amiteshsinghku...", modifier = Modifier)
            Spacer(modifier = Modifier.size(20.dp))
            ProfileSection()
            Spacer(modifier = Modifier.size(20.dp))
        }
        PostTabView(
            list = listOf(
                TabEntity(
                    image = painterResource(id = R.drawable.ic_grid),
                    text = "Posts"
                ),
                TabEntity(
                    image = painterResource(id = R.drawable.profile),
                    text = "Posts"
                )

            )
        ) {
            selectedTabIndex = it
        }
        when (selectedTabIndex) {
            0 -> PostSection(
                posts = listOf(
                    painterResource(id = R.drawable.profile_picture),
                    painterResource(id = R.drawable.profile_picture),
                    painterResource(id = R.drawable.profile_picture),
                    painterResource(id = R.drawable.profile_picture),
                    painterResource(id = R.drawable.profile_picture),
                    painterResource(id = R.drawable.profile_picture),
                    painterResource(id = R.drawable.profile_picture),
                    painterResource(id = R.drawable.profile_picture),
                    painterResource(id = R.drawable.profile_picture),
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )

            1 -> PostSection(null, modifier = Modifier.fillMaxWidth())
        }

        BottomMenu(
            list = listOf(
                BottomMenuEntity(
                    "Home", R.drawable.ic_baseline_home_24
                ),
                BottomMenuEntity(
                    "Search", R.drawable.baseline_search_24
                ),
                BottomMenuEntity(
                    "Post", R.drawable.ic_square_plus
                ),
                BottomMenuEntity(
                    "Reels", R.drawable.ic_reels
                ),
                BottomMenuEntity(
                    "Profile", R.drawable.profile_picture
                )
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            PlusOverlayImage(
                painterResource(id = R.drawable.profile_picture),
                modifier = Modifier
                    .size(100.dp)
                    .weight(3f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            ProfileStat(modifier = Modifier.weight(7f))
        }
        Spacer(modifier = Modifier.width(16.dp))
        ProfileDescription(
            userName = "Amitesh Singh",
            description = "Android Developer| 5+ years of Coding experience | Kotlin & Java Enthusiast | Turning idea into Apps!"
        )
        Spacer(modifier = Modifier.height(16.dp))
        ButtonSection()
    }
}

@Composable
fun ProfileDescription(
    userName: String,
    description: String
) {
    val letterSpacing = 0.5.sp
    val lineHeight = 20.sp
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        Text(
            text = userName,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        Text(
            text = description,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Start,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
    }

}

@Composable
fun PlusOverlayImage(
    image: Painter,
    modifier: Modifier = Modifier
) {
    // Profile Image
    Box(contentAlignment = Alignment.BottomEnd, modifier = modifier) {

        RoundImage(image = image, modifier = modifier)

        // Plus Icon overlay
        Box(
            modifier = Modifier
                .size(26.dp)
                .clip(CircleShape)
                .background(Color.White)
                .padding(2.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus_circle_svg_com),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = colorResource(R.color.sky_blue)
            )

        }

    }
}

@Composable
fun RoundImage(
    image: Painter,
    modifier: Modifier = Modifier,
    isBottomItem: Boolean = false,
    isBottomItemSelected: Boolean = false
) {
    Image(
        painter = image,
        contentDescription = null,
        modifier = if (!isBottomItem) {
            modifier
                .aspectRatio(
                    1f,
                    matchHeightConstraintsFirst = true
                )
                .padding(3.dp)
                .clip(shape = CircleShape)
        } else {
            modifier
                .aspectRatio(
                    1f,
                    matchHeightConstraintsFirst = true
                )
                .border(
                    width = 1.dp,
                    color = if (isBottomItemSelected) Color.Black else Color.Gray,
                    shape = CircleShape
                )
                .clip(shape = CircleShape)
                .size(24.dp)
        }
    )
}

@Composable
fun ProfileStat(modifier: Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier,
    ) {
        ProfileItem(PostEntity("posts", 34))
        ProfileItem(PostEntity("followers", 161))
        ProfileItem(PostEntity("following", 334))
    }
}

@Composable
fun ProfileItem(postEntity: PostEntity) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = postEntity.postCount.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = postEntity.postType,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )

    }
}

@Composable
fun TopBar(
    userId: String,
    modifier: Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_lock_open_24),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )

        Text(
            text = userId,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp, maxLines = 1,
        )

        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_thread_logo),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_square_plus),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )

        Icon(
            painter = painterResource(id = R.drawable.baseline_density_medium_24),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )

    }
}

@Composable
fun ButtonSection(
    modifier: Modifier = Modifier
) {
    val minWidth = 95.dp
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        ActionButton(
            text = "Edit Profile",
            modifier = Modifier
                .weight(1f)
                .defaultMinSize(minWidth = minWidth)
                .height(IntrinsicSize.Max)
        )
        ActionButton(
            text = "Share Profile",
            modifier = Modifier
                .weight(1f)
                .defaultMinSize(minWidth = minWidth)
                .height(IntrinsicSize.Max)
        )
        IconButton(
            icon = painterResource(R.drawable.baseline_person_add_24),
            modifier = Modifier
                .height(IntrinsicSize.Max)
        )
    }
}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String? = null
) {
    val context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,

        modifier = modifier
            .background(
                Color(
                    ContextCompat.getColor(
                        context,
                        R.color.light_gray_button_background
                    )
                ), RoundedCornerShape(10.dp)
            )
            .padding(6.dp)
            .clickable {
                Toast
                    .makeText(context, "$text Clicked", Toast.LENGTH_SHORT)
                    .show()
            }
    ) {
        text?.let { texts ->
            Text(
                text = texts,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

    }

}

@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    icon: Painter
) {
    val context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .background(
                Color(
                    ContextCompat.getColor(
                        context,
                        R.color.light_gray_button_background
                    )
                ), RoundedCornerShape(10.dp)
            )
            .padding(6.dp)
            .clickable {
                Toast
                    .makeText(context, "Add Friend Clicked", Toast.LENGTH_SHORT)
                    .show()
            }
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = Color.Black
        )
    }
}

@Composable
fun PostTabView(
    modifier: Modifier = Modifier,
    list: List<TabEntity> = listOf(),
    onTabSelected: (slectedIndex: Int) -> Unit
) {
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    val inactiveColor = Color(0xFF777777)
    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.Transparent,
        contentColor = Color.Black,
        modifier = modifier,
    ) {
        list.forEachIndexed { index, tabEntity ->
            Tab(
                selected = selectedTabIndex == index,
                selectedContentColor = Color.Black,
                unselectedContentColor = inactiveColor,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(index)
                }
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = tabEntity.image,
                        contentDescription = tabEntity.text,
                        tint = if (selectedTabIndex == index) Color.Black else inactiveColor,
                        modifier = Modifier
                            .size(30.dp)
                    )
                }

            }
        }
    }

}

@Composable
fun PostSection(
    posts: List<Painter>? = null,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    if (posts?.isNotEmpty() == true) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = modifier
                .scale(1.07f)
                .padding(10.dp)
        ) {
            items(posts.size) {
                Image(
                    painter = posts[it],
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .border(
                            width = 1.dp,
                            color = Color.White
                        )
                )
            }
        }
    } else {
        Image(
            painter = painterResource(R.drawable.empty_view),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
                .border(
                    width = 1.dp,
                    color = Color.White
                )
        )
    }
}

@Composable
fun BottomMenu(
    list: List<BottomMenuEntity>,
    modifier: Modifier = Modifier,
    activeIconColor: Color = Color.Black,
    inactiveIconColor: Color = Color.Gray,
    initialSelectedItem: Int = 0
) {
    val context = LocalContext.current
    var selectedItemIndex by remember {
        mutableIntStateOf(initialSelectedItem)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        list.forEachIndexed { index, bottomMenuEntity ->
            BottomMenuItem(
                item = bottomMenuEntity,
                isSelected = selectedItemIndex == index,
                activeIconColor = activeIconColor,
                inactiveIconColor = inactiveIconColor
            ) {
                selectedItemIndex = index
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
            }
        }

    }


}

@Composable
fun BottomMenuItem(
    item: BottomMenuEntity,
    isSelected: Boolean = false,
    activeIconColor: Color = Color.Black,
    inactiveIconColor: Color = Color.Gray,
    onItemSelectedIdx: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemSelectedIdx()
        }
    ) {
        if (item.title != "Profile") {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeIconColor else inactiveIconColor,
                modifier = Modifier.size(24.dp)

            )
        } else {
            RoundImage(
                image = painterResource(id = item.iconId),
                modifier = Modifier.size(24.dp),
                isBottomItem = true,
                isBottomItemSelected = isSelected

            )
        }
    }
}

