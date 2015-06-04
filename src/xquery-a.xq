<correct>{
for $i in doc("E:/IntelliJProjects/SSD3/resources/jeopardy.xml")//game[@session="af21de5"]//givenanswer[@player="Bart"]
let $a := doc("E:/IntelliJProjects/SSD3/resources/jeopardy.xml")//answer[@correct="yes"]
where $i/text()=$a
return
    $i
}</correct>


