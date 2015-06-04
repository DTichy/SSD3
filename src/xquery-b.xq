<stats>{
    for $games in doc("E:/IntelliJProjects/SSD3/resources/jeopardy.xml")//game
    return
        <session id="{$games/@session}">{
            for $player in $games//player
            let $rightAnswers := doc("E:/IntelliJProjects/SSD3/resources/jeopardy.xml")//answer[@correct = "yes"]
            let $answers := count($games//givenanswer[text() = $rightAnswers and @player = $player/@ref])
            return
                if($answers>0)
                then <correct player="{$player/@ref}">{$answers}</correct>
                else ()

        }
        </session>
    }</stats>

