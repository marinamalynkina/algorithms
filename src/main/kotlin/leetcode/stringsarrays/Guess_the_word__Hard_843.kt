package leetcode.stringsarrays

import java.util.PriorityQueue

fun main(args: Array<String>) {

//    println(Hard_T843_GuessTheWord().findSecretWord(
//        arrayOf("wichbx","oahwep","tpulot","eqznzs","vvmplb","eywinm","dqefpt","kmjmxr","ihkovg","trbzyb","xqulhc","bcsbfw","rwzslk","abpjhw","mpubps","viyzbc","kodlta","ckfzjh","phuepp","rokoro","nxcwmo","awvqlr","uooeon","hhfuzz","sajxgr","oxgaix","fnugyu","lkxwru","mhtrvb","xxonmg","tqxlbr","euxtzg","tjwvad","uslult","rtjosi","hsygda","vyuica","mbnagm","uinqur","pikenp","szgupv","qpxmsw","vunxdn","jahhfn","kmbeok","biywow","yvgwho","hwzodo","loffxk","xavzqd","vwzpfe","uairjw","itufkt","kaklud","jjinfa","kqbttl","zocgux","ucwjig","meesxb","uysfyc","kdfvtw","vizxrv","rpbdjh","wynohw","lhqxvx","kaadty","dxxwut","vjtskm","yrdswc","byzjxm","jeomdc","saevda","himevi","ydltnu","wrrpoc","khuopg","ooxarg","vcvfry","thaawc","bssybb","ccoyyo","ajcwbj","arwfnl","nafmtm","xoaumd","vbejda","kaefne","swcrkh","reeyhj","vmcwaf","chxitv","qkwjna","vklpkp","xfnayl","ktgmfn","xrmzzm","fgtuki","zcffuv","srxuus","pydgmq"),
//        M("ccoyyo")
//    ))

//    val test2List = arrayOf("gaxckt","trlccr","jxwhkz","ycbfps","peayuf","yiejjw","ldzccp","nqsjoa","qrjasy","pcldos","acrtag","buyeia","ubmtpj","drtclz","zqderp","snywek","caoztp","ibpghw","evtkhl","bhpfla","ymqhxk","qkvipb","tvmued","rvbass","axeasm","qolsjg","roswcb","vdjgxx","bugbyv","zipjpc","tamszl","osdifo","dvxlxm","iwmyfb","wmnwhe","hslnop","nkrfwn","puvgve","rqsqpq","jwoswl","tittgf","evqsqe","aishiv","pmwovj","sorbte","hbaczn","coifed","hrctvp","vkytbw","dizcxz","arabol","uywurk","ppywdo","resfls","tmoliy","etriev","oanvlx","wcsnzy","loufkw","onnwcy","novblw","mtxgwe","rgrdbt","ckolob","kxnflb","phonmg","egcdab","cykndr","lkzobv","ifwmwp","jqmbib","mypnvf","lnrgnj","clijwa","kiioqr","syzebr","rqsmhg","sczjmz","hsdjfp","mjcgvm","ajotcx","olgnfv","mjyjxj","wzgbmg","lpcnbj","yjjlwn","blrogv","bdplzs","oxblph","twejel","rupapy","euwrrz","apiqzu","ydcroj","ldvzgq","zailgu","xgqpsr","wxdyho","alrplq","brklfk")
//
//    println(Hard_T843_GuessTheWord().findSecretWord(
//        test2List,
//        M("hbaczn")
//    ))
//    val test3List = arrayOf("aaaaga","aaaaka","aaauaa","aaaaoa","aafaaa","aaaaza","aaaava","agaaaa","aaagaa","aaaaqa","aaaaca","aaaaua","apaaaa","aawaaa","aaaaba","aaaqaa","aayaaa","aaaaja","aaacaa","aaayaa","aaaeaa","aavaaa","aasaaa","aaaapa","aaaaxa","aeaaaa","aaxaaa","akaaaa","aaaoaa","aazaaa","anaaaa","aaaala","aaraaa","aaaata","aaaaia","ajaaaa","aaaaaa","ahaaaa","aaaraa","aaaiaa","aanaaa","alaaaa","aakaaa","aiaaaa","aajaaa","aaakaa","axaaaa","aaqaaa","aaamaa","aapaaa","aaafaa","aaasaa","aadaaa","amaaaa","aaaaea","aabaaa","aaaama","asaaaa","acaaaa","aaiaaa","avaaaa","afaaaa","aoaaaa","aamaaa","aaaasa","aaawaa","azaaaa","aataaa","aaeaaa","aaaafa","aaahaa","aaalaa","aaaana","aaanaa","aaabaa","aaaada","auaaaa","aaapaa","awaaaa","ayaaaa","adaaaa","aaavaa","aagaaa","aauaaa","abaaaa","aaadaa","aqaaaa","aaaxaa","aaaawa","aaajaa","araaaa","aahaaa","aaaaha","aacaaa","aaaara","aaoaaa","ataaaa","aaaaya","aalaaa","aaazaa")
//
//    println(Hard_T843_GuessTheWord().findSecretWord(
//        test3List,
//        M("aaaata")
//    ))

    val test4List  = arrayOf("eykdft","gjeixr","eksbjm","mxqhpk","tjplhf","ejgdra","npkysm","jsrsid","cymplm","vegdgt","jnhdvb","jdhlzb","sgrghh","jvydne","laxvnm","xbcliw","emnfcw","pyzdnq","vzqbuk","gznrnn","robxqx","oadnrt","kzwyuf","ahlfab","zawvdf","edhumz","gkgiml","wqqtla","csamxn","bisxbn","zwxbql","euzpol","mckltw","bbnpsg","ynqeqw","uwvqcg","hegrnc","rrqhbp","tpfmlh","wfgfbe","tpvftd","phspjr","apbhwb","yjihwh","zgspss","pesnwj","dchpxq","axduwd","ropxqf","gahkbq","yxudiu","dsvwry","ecfkxn","hmgflc","fdaowp","hrixpl","czkgyp","mmqfao","qkkqnz","lkzaxu","cngmyn","nmckcy","alpcyy","plcmts","proitu","tpzbok","vixjqn","suwhab","dqqkxg","ynatlx","wmbjxe","hynjdf","xtcavp","avjjjj","fmclkd","ngxcal","neyvpq","cwcdhi","cfanhh","ruvdsa","pvzfyx","hmdmtx","pepbsy","tgpnql","zhuqlj","tdrsfx","xxxyle","zqwazc","hsukcb","aqtdvn","zxbxps","wziidg","tsuxvr","florrj","rpuorf","jzckev","qecnsc","rrjdyh","zjtdaw","dknezk")
    println(Hard_T843_GuessTheWord().findSecretWord(
        test4List,
        M("cymplm")
    ))

}
var count = 0

class M(val key: String): Master {
    override fun guess(word: String): Int {
        count++
        println(count)
        return getMatches(key, word)
    }
}

interface Master {
    fun guess(word: String): Int
}
class Hard_T843_GuessTheWord {
    fun findSecretWord(words: Array<String>, master: Master) {
        val n = words.size
        val matrix = Array(n){IntArray(n)}
        val maxFit = IntArray(n) {0}
        for(i in 0 until n){
            for(j in i until n){
                val score = getMatches(words[i], words[j])
                if (i != j) {
                    maxFit[i] += score
                    maxFit[j] += score
                }
                matrix[i][j] = score
                matrix[j][i] = score
            }
        }
        val maxFitQ = PriorityQueue<IntArray> { o1, o2 -> o2[1] - o1[1] }
        maxFitQ.addAll(maxFit.mapIndexed { index, score -> intArrayOf(index, score) } )

        val visited = BooleanArray(n) {false}
        var match = 0
        var i = 0
        while (i < 15 && match != 6) {
            var index = 0
            var isVisited = true
            while (isVisited && maxFitQ.isNotEmpty()) {
                val indexScore = maxFitQ.remove()
                index = indexScore[0]
                if(!visited[index]) isVisited = false
            }

            val word = words[index]
            match = master.guess(word)
            visited[index] = true
            println("$word matches=$match")
            if (match == 6) return
            val newCandidates = mutableListOf<String>()
            for (newWordindex in 0..words.size-1) {
                val newWord = words[newWordindex]
                if (newWord != word && match == getMatches(word, newWord)) newCandidates.add(newWord)
                else {
                    visited[newWordindex] = true
                }
            }
            i++
        }
    }

    private fun getMatches(word1: String, word2: String): Int {
        var match = 0
        for (i in 0..word1.length-1) {
            if (word1[i] == word2[i]) match++;
        }
        return match
    }

}

fun getMatches(word1: String, word2: String): Int {
    var match = 0
    for (i in 0..word1.length-1) {
        if (word1[i] == word2[i]) match ++;
    }
    return match
}