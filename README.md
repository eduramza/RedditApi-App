# RedditApi-App

O aplicativo foi criado tentando trazer algumas das ferramentas e bibliotecas mais atuais do mercado, mesmo ainda não tendo muita experiencia com elas optei trazer os componentes
do Navigation, ViewBinding, Kotlin Flow, entre outras.
O layout do aplicativo acabou não ficando tão rico como eu gostaria, pois acabei não tendo muito tempo no final para poder implementar ajustes que o deixassem melhor, porém, fiz o 
meu melhor possível para deixa-lo apresentavel dentro do prazo que eu mesmo estipulei para a entrega.
Os testes unitários eu optei apenas por faze-los sobre a primeira tela por conta de tempo, pois os testes da viewmodel e da repository das classes de detalhes são muito parecidos.
O teste em questão foi muito util para pegar um problema referente a minha view model pois a partir dele pude descobrir, 
por exemplo que o livedata next não estava retornando o valor do seu correspondente _next como pode ser visto no hitorico do ultimo commit.

#libs e plugins utilizados
JSON TO KOTLIN CONVERTER -> Eu tenho utilizado bastante esse plugin para adiantar o passo na hora da criação das data class baseadas na resposta das apis, o mesmo poupa um bom tempo para o desenvolvedor.

JETPACK NAVIGATION -> Recentemente comecei a estudar utilizando essa ferramenta disponibilizada pelo google, embora ainda não tenha total dominio optei por utiliza-la por acreditar
que ela é uma boa ferramenta e uma boa alternativa para quem quer se arriscar sainda um pouco da zona de conforto dos Fragments padrões. Além de facilitar muito o trabalho de
animações e transições entre framgnets de uma forma intuitiva e amigavel (embora eu tenha esquecido de adiconar animações)

MATERIAL DESIGN -> Utilizei essa lib para ciração dos cards utilizados tanto na listagem de posts como na de comentáios, são bem simples e elegantes de implantar.

COROUTINES -> Tenho familiaridade em utiliza-la, tanto para requisições ou até para gerenciamento de threads quando possivel.

RETROFIT -> Escolhida pela facilidade e familiaridade.

LIFECYCLE (VIEWMODEL) -> Utilizado mais por utimamente meus projetos tem sido baseados na arquitetura MVVM do que MVP por exemplo.

KOIN -> Tenho utilizado muito o Koin em projetos pessoais, acabei me familiarizando pois ele atende minhas necessidades basicas de injeção perfeitamente.

GLIDE -> A escolha do glide para download de imagens foi por conta da sua eficiencia em comparação com outras libs como Picasso e Coil, em um geral, assim como as demais ela é
bem facil de ser implantada, e possui um desempenho superior conforme benchmark : https://proandroiddev.com/coil-vs-picasso-vs-glide-get-ready-go-774add8cfd40.

MOCKITO -> Para os testes unitários optei por utilizado mockito ao invés de mockk pois tenho mais familiaridade com o mesmo.

BARISTA -> A conheci recentemente em um curso na Udemy e a utilizei pela sua facilidade em auxiliar coisas que as vezes são complexas para os testes de UI. Ela consegue suprir
principalmente testes da RecyclerView de forma prática (Os testes da UI eu também não dei muito fóco, realizei apenas como exemplos uns testes na tela de lista)

ESPRESSO e IDLING RESOURCES -> Como padrão para auxiliar nos testes de interface.

#Arquiteura
Como dito anteriormente a arquitetura utilizada foi um MVVM de uma forma simples e intuitiva, além disso, para o desenvolvimento da tela de listagem eu utilizei TDD 
(Como pode ser visto analisando a ordem das alterações nos primeiros commits), a abordagem do TDD trás muitos beneficios, como o principal é o exercicio do pensamento em unidade,
tornando assim as coisas cada vez mais simples, sem complicar o que não precisa.




