package com.igorwojda.tree.avl

import org.amshove.kluent.shouldEqual
import org.junit.Test

private class AvlTree<E : Comparable<E>> {
    var root: Node<E>? = null

    fun add(element: E) {
        val node = Node(element)

        if (root == null) {
            root = node
            return
        }

        var currentNode = root

        while (currentNode != null) {
            if (node.data > currentNode.data) {
                if (currentNode.right == null) {
                    currentNode.right = node
                    node.parent = currentNode
                    break
                } else {
                    currentNode = currentNode.right
                }
            } else if (node.data < currentNode.data) {
                if (currentNode.left == null) {
                    currentNode.left = node
                    node.parent = currentNode
                    break
                } else {
                    currentNode = currentNode.left
                }
            } else {
                break
            }
        }
    }

    fun removeMax(): E? {
        TODO("not implemented")
    }

    private fun getParentIndex(index: Int): Int = TODO("not implemented")

    private fun getLeftChildIndex(index: Int): Int = TODO("not implemented")

    private fun getRightChildIndex(index: Int): Int = TODO("not implemented")

    fun isBalanced(): Boolean {
        TODO("Imlement")
    }

    fun isEmpty() = root == null
}

private data class Node<E>(
    val data: E,
    var parent: Node<E>? = null,
    var left: Node<E>? = null,
    var right: Node<E>? = null
)

class AvlTreeTest {
    @Test
    fun `build correct avl tree without balancing 5 3 8`() {
        AvlTree<Int>().apply {
            root shouldEqual null

            add(5)
            add(3)
            add(8)

            // -------AVL tree -----------
            //
            //            5
            //          /   \
            //         3     8
            //
            // --------------------------

            // Test 5
            root?.data shouldEqual 5
            root?.parent shouldEqual null
            root?.left?.data shouldEqual 3
            root?.right?.data shouldEqual 8

            // Test node 3
            root?.left?.apply {
                data shouldEqual 3
                parent?.data shouldEqual 5
                left?.data shouldEqual null
                right?.data shouldEqual null
            }

            // Test node 8
            root?.right?.apply {
                data shouldEqual 8
                parent?.data shouldEqual 5
                left?.data shouldEqual null
                right?.data shouldEqual null
            }
        }
    }

    @Test
    fun `build correct avl tree without balancing 5 3 8 1 4 7 9`() {
        AvlTree<Int>().apply {
            root shouldEqual null

            add(5)
            add(3)
            add(8)
            add(1)
            add(4)
            add(7)
            add(9)

            // -------AVL tree -----------
            //
            //            5
            //          /   \
            //         3     8
            //        / \   / \
            //       1   4 7   9
            //
            // --------------------------

            // Test 5
            root?.data shouldEqual 5
            root?.parent shouldEqual null
            root?.left?.data shouldEqual 3
            root?.right?.data shouldEqual 8

            // Test node 3
            val node3 = root?.left?.apply {
                data shouldEqual 3
                parent?.data shouldEqual 5
                left?.data shouldEqual 1
                right?.data shouldEqual 4
            }

            // Test node 8
            val node8 = root?.right?.apply {
                data shouldEqual 8
                parent?.data shouldEqual 5
                left?.data shouldEqual 7
                right?.data shouldEqual 9
            }

            // Test node 1
            node3?.left?.apply {
                data shouldEqual 1
                parent?.data shouldEqual 3
                left?.data shouldEqual null
                right?.data shouldEqual null
            }

            // Test node 4
            node3?.right?.apply {
                data shouldEqual 4
                parent?.data shouldEqual 3
                left?.data shouldEqual null
                right?.data shouldEqual null
            }

            // Test node 7
            node8?.left?.apply {
                data shouldEqual 7
                parent?.data shouldEqual 8
                left?.data shouldEqual null
                right?.data shouldEqual null
            }

            // Test node 9
            node8?.right?.apply {
                data shouldEqual 9
                parent?.data shouldEqual 8
                left?.data shouldEqual null
                right?.data shouldEqual null
            }
        }
    }

    @Test
    fun `detect avl left inbalance`() {
        AvlTree<Int>().apply {
            isBalanced() shouldEqual true
            add(4)
            isBalanced() shouldEqual true
            add(3)
            isBalanced() shouldEqual true
            add(2)
            isBalanced() shouldEqual true
            add(1)
            isBalanced() shouldEqual false

            // -------AVL tree -----------
            //
            //           4
            //          /
            //         3
            //        /
            //       2
            //      /
            //     1
            //
            // --------------------------
        }
    }

    @Test
    fun `detect avl right inbalance`() {
        AvlTree<Int>().apply {
            isBalanced() shouldEqual true
            add(4)
            isBalanced() shouldEqual true
            add(6)
            isBalanced() shouldEqual true
            add(10)
            isBalanced() shouldEqual true
            add(11)
            isBalanced() shouldEqual false

            // -------AVL tree -----------
            //
            //           4
            //            \
            //             6
            //              \
            //               9
            //                \
            //                 11
            //
            // --------------------------
        }
    }

//    @Test
//    fun `left rotate 4 around 6`() {
//        AvlTree<Int>().apply {
//            add(4)
//            add(6)
//            add(10)
//
//            // -------AVL tree -----------
//            //
//            //           4
//            //            \
//            //             6
//            //              \
//            //               9
//            //
//            // --------------------------
//
//            items[0] shouldEqual 9
//            items[1] shouldEqual 7
//            items[2] shouldEqual 6
//            items[3] shouldEqual 3
//            items[4] shouldEqual 2
//            items[5] shouldEqual 4
//            items.size shouldEqual 6
//        }
//    }
}
