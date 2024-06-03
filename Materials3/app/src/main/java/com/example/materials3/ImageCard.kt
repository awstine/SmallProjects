@file:Suppress("PreviewAnnotationInFunctionWithParameters")

package com.example.materials3

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Suppress("ktlint:standard:function-naming")
@OptIn(ExperimentalLayoutApi::class)
@Composable
@Preview
fun ImageCard(
    title: String,
    description: String,
    imageData: ImageData,
    modifier: Modifier = Modifier,
)  {
    Card(
        modifier = Modifier,
        colors =
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
        shape = MaterialTheme.shapes.large,
    ) {
        Image(
            painter = painterResource(id = imageData.imageResource),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.large)
                    )
            }
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            // Title
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(4.dp))
            FlowRow(
                modifier =
                    Modifier
                        .fillMaxWidth(),
                
                // mainAxisSpacing = 8.dp,
                // mainAxisSize = LayoutSize.Expand
            ) {
                AssistChip(
                    onClick = {},
                    colors =
                        AssistChipDefaults.assistChipColors(
                            leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = null,
                        )
                    },
                    label = {
                        Text("Mark as favourite")
                    },
                )
                Spacer(modifier = Modifier.width(8.dp))
                AssistChip(
                    onClick = {},
                    colors =
                        AssistChipDefaults.assistChipColors(
                            leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Share,
                            contentDescription = null,
                        )
                    },
                    label = {
                        Text("Share With others")
                    },
                )
            }
        }
    }
