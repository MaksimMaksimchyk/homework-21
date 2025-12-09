package com.example.homework21.MultiType

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.homework21.databinding.AuthorViewBinding
import com.example.homework21.databinding.ButtonTextViewBinding
import com.example.homework21.databinding.ImageTextViewBinding
import kotlin.random.Random

class MyMultiAdapter(private var data: List<MyData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_AUTHOR = 0
        private const val TYPE_BUTTON = 1
        private const val TYPE_IMAGE = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is Author -> TYPE_AUTHOR
            is ButtonText -> TYPE_BUTTON
            is ImageText -> TYPE_IMAGE
            else -> throw IllegalArgumentException("Unknown type")
        }
    }

    // ViewHolder for Author
    class AuthorViewHolder(private val binding: AuthorViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(author: Author) {
            binding.author.text = author.name
            binding.sampleText.text = author.text
        }
    }

    // ViewHolder for ButtonText
    class ButtonTextViewHolder(private val binding: ButtonTextViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(button: ButtonText) {
            binding.sampleText.text = button.text
        }
    }

    // ViewHolder for ImageText
    class ImageTextViewHolder(private val binding: ImageTextViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: ImageText) {
            binding.sampleText.text = image.text
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_AUTHOR -> {
                val binding = AuthorViewBinding.inflate(layoutInflater, parent, false)
                AuthorViewHolder(binding)
            }

            TYPE_BUTTON -> {
                val binding = ButtonTextViewBinding.inflate(layoutInflater, parent, false)
                ButtonTextViewHolder(binding)
            }

            TYPE_IMAGE -> {
                val binding = ImageTextViewBinding.inflate(layoutInflater, parent, false)
                ImageTextViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (holder) {
            is AuthorViewHolder -> holder.bind(data[position] as Author)
            is ButtonTextViewHolder -> holder.bind(data[position] as ButtonText)
            is ImageTextViewHolder -> holder.bind(data[position] as ImageText)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateList(newData: List<MyData>) {
        val diffResult = DiffUtil.calculateDiff(MyDiffUtilCallback(data, newData))
        data = newData
        diffResult.dispatchUpdatesTo(this)
    }

}

open class MyData(val id: Long = Random.nextLong())
data class Author(
    val name: String = "Sample Name",
    val text: String = "Random Sample Text",
) : MyData()

data class ButtonText(val text: String = "Random Sample Text") :
    MyData()

data class ImageText(val text: String = "Random Sample Text") :
    MyData()