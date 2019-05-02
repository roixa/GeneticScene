package com.example.demo.model


object TestAlg {
    fun check(exp: String): Boolean {
        var ret = false
        val leftArgs = emptyList<Char>().toMutableList()
        val rightArgs = emptyList<Char>().toMutableList()
        exp.toCharArray().forEach {
            if (isLeftValue(it)) {
                leftArgs.add(it)
            } else {
                rightArgs.add(it)
            }
            ret = !isExpressionNotClosed(leftArgs, rightArgs)
            if (ret) {
                leftArgs.clear()
                rightArgs.clear()
            }
        }
        return ret
    }

    fun isExpressionNotClosed(left: List<Char>, right: List<Char>): Boolean {
        if (left.size == right.size) {
            left.forEachIndexed { i, item ->
                val rightValue = right[right.size.minus(i).minus(1)]
                if (!isEqualBracketType(item, rightValue)) {
                    return true
                }
            }
            return false
        }
        return true
    }

    fun isLeftValue(c: Char): Boolean {
        return c == '{' || c == '[' || c == '('
    }

    fun isEqualBracketType(a: Char, b: Char): Boolean {
        val one = a == '{' && b == '}' || b == '{' && a == '}'
        val two = a == '[' && b == ']' || b == '[' && a == ']'
        val three = a == '(' && b == ')' || b == '(' && a == ')'
        return one || two || three
    }
}