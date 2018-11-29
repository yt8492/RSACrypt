import java.lang.NumberFormatException
import java.math.BigInteger
import java.util.*

fun main() {
    val p = readLine()?.let { input ->
        try {input.toBigInteger().let {
                if (it.isProbablePrime(10)) {
                    it
                } else {
                    it.nextProbablePrime()
                }
            }
        } catch (e: NumberFormatException) {
            BigInteger.probablePrime(10, Random())
        }
    } ?: BigInteger.probablePrime(10, Random())
    val q = readLine()?.let { input ->
        try {input.toBigInteger().let {
            if (it.isProbablePrime(10)) {
                it
            } else {
                it.nextProbablePrime()
            }
        }
        } catch (e: NumberFormatException) {
            BigInteger.probablePrime(10, Random())
        }
    } ?: BigInteger.probablePrime(10, Random())
    println("p = $p")
    println("q = $q")
    val n = p * q
    println("n = $n")
    val figh = (p - 1.toBigInteger()) * (q - 1.toBigInteger())
    println("φ = $figh")
    val e = getBiggestPrime(figh).nextProbablePrime()
    println("e = $e")
    val d = exGcd(e to figh).first.let {
        if (it >= 0.toBigInteger()) {
            it
        } else {
            it + figh
        }
    }
    println("d = $d")
    println("de mod φ = ${(d * e) % figh}")

}

fun exGcd(pair: Pair<BigInteger, BigInteger>): Pair<BigInteger, BigInteger> {
    if (pair.second == 0.toBigInteger()) {
        return 1.toBigInteger() to 0.toBigInteger()
    } else {
        val q = pair.first / pair.second
        val r = pair.first % pair.second
        val p = exGcd(pair.second to r)
        val u = p.second
        val v = p.first - q * u
        return u to v
    }
}

fun getBiggestPrime(a: BigInteger): BigInteger {
    return if (a.isProbablePrime(10)) {
        a
    } else {
        getBiggestPrime(a, 2.toBigInteger())
    }
}

fun getBiggestPrime(a: BigInteger, b: BigInteger): BigInteger{
    return if (a % b == 0.toBigInteger()) {
        if (a == b) {
            a
        } else {
            getBiggestPrime(a / b, b)
        }
    } else {
        getBiggestPrime(a, b.nextProbablePrime())
    }
}
