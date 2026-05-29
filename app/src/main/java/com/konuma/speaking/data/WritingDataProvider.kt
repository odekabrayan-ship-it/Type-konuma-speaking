package com.konuma.speaking.data

import com.konuma.speaking.model.BilingualSentence
import com.konuma.speaking.model.WritingDomain
import com.konuma.speaking.model.WritingTopic

object WritingDataProvider {
    val topics = listOf(
        // A1 Level
        WritingTopic(
            id = "w1", level = "A1", domain = WritingDomain.PERSONAL,
            titleTr = "Kendinizi Tanıtın", titleEn = "Introduce Yourself",
            task = "Kendinizi tanıtın. Adınızı, yaşınızı, ülkenizi ve hobilerinizi yazın.",
            usefulWords = listOf("Adım", "Yaşındayım", "Öğrenciyim", "Seviyorum", "Yaşıyorum"),
            sentenceModel = listOf(
                BilingualSentence("Hello.", "Merhaba."),
                BilingualSentence("My name is Brayan.", "Benim adım Brayan."),
                BilingualSentence("I am 24 years old.", "Ben 24 yaşındayım."),
                BilingualSentence("I am from Kenya.", "Ben Kenya’danım."),
                BilingualSentence("I live in Adana.", "Adana’da yaşıyorum."),
                BilingualSentence("I study biotechnology at university.", "Üniversitede biyoteknoloji okuyorum."),
                BilingualSentence("I am doing a master’s degree.", "Yüksek lisans yapıyorum."),
                BilingualSentence("In my free time, I like listening to music.", "Boş zamanlarımda müzik dinlemeyi severim."),
                BilingualSentence("I also like reading books.", "Ayrıca kitap okumayı severim."),
                BilingualSentence("I spend time with my friends.", "Arkadaşlarımla vakit geçiririm."),
                BilingualSentence("I am learning Turkish.", "Türkçe öğreniyorum."),
                BilingualSentence("Because I study in Turkey.", "Çünkü Türkiye’de okuyorum."),
                BilingualSentence("Nice to meet you.", "Tanıştığımıza memnun oldum.")
            ),
            grammarFocus = "Şimdiki Zaman", wordCountGuide = "50-80 kelime"
        ),
        WritingTopic(
            id = "w2", level = "A1", domain = WritingDomain.PERSONAL,
            titleTr = "Ailenizi Tanıtın", titleEn = "Introduce Your Family",
            task = "Ailenizi tanıtın. Aile üyelerinizi, işlerini ve birbirinize olan sevginizi yazın.",
            usefulWords = listOf("Ailem", "Annem", "Babam", "Kardeşim", "Öğretmen", "Öğrenci", "Seviyoruz"),
            sentenceModel = listOf(
                BilingualSentence("Hello.", "Merhaba."),
                BilingualSentence("I want to introduce my family.", "Ailemi tanıtmak istiyorum."),
                BilingualSentence("My family is small.", "Ailem küçük."),
                BilingualSentence("I have a mother.", "Bir annem var."),
                BilingualSentence("I have a father.", "Bir babam var."),
                BilingualSentence("I have one brother.", "Bir erkek kardeşim var."),
                BilingualSentence("My mother is a teacher.", "Annem öğretmendir."),
                BilingualSentence("My father is a worker.", "Babam işçidir."),
                BilingualSentence("My brother is a student.", "Kardeşim öğrencidir."),
                BilingualSentence("We live together.", "Birlikte yaşıyoruz."),
                BilingualSentence("We love each other.", "Birbirimizi seviyoruz."),
                BilingualSentence("My family is very important to me.", "Ailem benim için çok önemlidir."),
                BilingualSentence("Thank you.", "Teşekkür ederim.")
            ),
            grammarFocus = "İsim Cümleleri", wordCountGuide = "50-80 kelime"
        ),
        WritingTopic(
            id = "w3", level = "A1", domain = WritingDomain.DAILY_LIFE,
            titleTr = "Günlük Rutininiz", titleEn = "Your Daily Routine",
            task = "Günlük rutininizden bahsedin. Sabahtan akşama neler yapıyorsunuz?",
            usefulWords = listOf("Kalkarım", "Yıkarım", "Kahvaltı", "Giderim", "Ders", "Dönerim"),
            sentenceModel = listOf(
                BilingualSentence("Hello.", "Merhaba."),
                BilingualSentence("I want to talk about my daily routine.", "Günlük rutinimden bahsetmek istiyorum."),
                BilingualSentence("I wake up early in the morning.", "Sabah erken kalkarım."),
                BilingualSentence("I wash my face.", "Yüzümü yıkarım."),
                BilingualSentence("I have breakfast.", "Kahvaltı yaparım."),
                BilingualSentence("I go to university.", "Üniversiteye giderim."),
                BilingualSentence("I study in the library.", "Kütüphanede ders çalışırım."),
                BilingualSentence("I come back home in the evening.", "Akşam eve dönerim."),
                BilingualSentence("Thank you.", "Teşekkür ederim.")
            ),
            grammarFocus = "Geniş Zaman", wordCountGuide = "60-100 kelime"
        ),
        WritingTopic(
            id = "w4", level = "A1", domain = WritingDomain.DAILY_LIFE,
            titleTr = "En Sevdiğiniz Yemek", titleEn = "Your Favorite Food",
            task = "En sevdiğiniz yemek hakkında yazın. Neden seviyorsunuz?",
            usefulWords = listOf("Lezzetli", "Genellikle", "Annem", "Severim", "Önemlidir"),
            sentenceModel = listOf(
                BilingualSentence("My favorite food is rice and chicken.", "En sevdiğim yemek pilav ve tavuktur."),
                BilingualSentence("I like eating this food very much.", "Bu yemeği çok severim."),
                BilingualSentence("It is delicious.", "Çök lezzetlidir."),
                BilingualSentence("My mother cooks it.", "Annem yapar."),
                BilingualSentence("Thank you.", "Teşekkür ederim.")
            ),
            grammarFocus = "Beğeni İfadeleri", wordCountGuide = "50-80 kelime"
        ),
        WritingTopic(
            id = "w5", level = "A1", domain = WritingDomain.SOCIAL,
            titleTr = "Arkadaşınızı Tanıtın", titleEn = "Introduce Your Friend",
            task = "Bir arkadaşınızı tanıtın. Adını, yaşını ve kişiliğini yazın.",
            usefulWords = listOf("Arkadaşım", "Adı", "Yaşındadır", "Naziktir", "Severim"),
            sentenceModel = listOf(
                BilingualSentence("My friend’s name is Ali.", "Arkadaşımın adı Ali."),
                BilingualSentence("He is 22 years old.", "O 22 yaşındadır."),
                BilingualSentence("He is very kind.", "O çok naziktir."),
                BilingualSentence("We spend time together.", "Birlikte vakit geçiririz."),
                BilingualSentence("Thank you.", "Teşekkür ederim.")
            ),
            grammarFocus = "Betimleme", wordCountGuide = "50-80 kelime"
        ),
        WritingTopic(
            id = "w6", level = "A1", domain = WritingDomain.SOCIETY,
            titleTr = "Şehrinizi Anlatın", titleEn = "Describe Your City",
            task = "Yaşadığınız şehri anlatın. Hava durumu ve önemli yerler hakkında yazın.",
            usefulWords = listOf("Şehrim", "Büyük", "Sıcak", "Ulaşım", "Güzel"),
            sentenceModel = listOf(
                BilingualSentence("I live in Adana.", "Adana’da yaşıyorum."),
                BilingualSentence("Adana is a big city.", "Adana büyük bir şehirdir."),
                BilingualSentence("The weather is hot.", "Hava sıcaktır."),
                BilingualSentence("Transport is easy.", "Ulaşım kolaydır."),
                BilingualSentence("Thank you.", "Teşekkür ederim.")
            ),
            grammarFocus = "Var / Yok", wordCountGuide = "60-100 kelime"
        ),
        // A2 Level
        WritingTopic(
            id = "w7", level = "A2", domain = WritingDomain.SOCIAL,
            titleTr = "Bir Tatil Anınız", titleEn = "A Holiday Memory",
            task = "Geçen yıl gittiğiniz bir tatili anlatın.",
            usefulWords = listOf("Geçen yıl", "Tatile gittim", "Ziyaret ettim", "Deneyimdi"),
            sentenceModel = listOf(
                BilingualSentence("Last year, I went on holiday.", "Geçen yıl tatile gittim."),
                BilingualSentence("The weather was very good.", "Hava çok güzeldi."),
                BilingualSentence("I visited many places.", "Birçok yeri ziyaret ettim."),
                BilingualSentence("It was a very nice experience.", "Çok güzel bir deneyimdi."),
                BilingualSentence("Thank you.", "Teşekkür ederim.")
            ),
            grammarFocus = "Belirli Geçmiş Zaman", wordCountGuide = "80-120 kelime"
        ),
        WritingTopic(
            id = "w8", level = "A1", domain = WritingDomain.PERSONAL,
            titleTr = "Hobileriniz", titleEn = "Your Hobbies",
            task = "Hobilerinizden bahsedin.",
            usefulWords = listOf("Müzik dinlemek", "Kitap okumak", "Mutlu eder", "Boş zaman"),
            sentenceModel = listOf(
                BilingualSentence("I have several hobbies.", "Birkaç hobim var."),
                BilingualSentence("I like reading books.", "Kitap okumayı severim."),
                BilingualSentence("My hobbies make me happy.", "Hobilerim beni mutlu eder."),
                BilingualSentence("Thank you.", "Teşekkür ederim.")
            ),
            grammarFocus = "Geniş Zaman", wordCountGuide = "60-100 kelime"
        ),
        WritingTopic(
            id = "w9", level = "A2", domain = WritingDomain.DAILY_LIFE,
            titleTr = "Ev Tanıtımı", titleEn = "Home Description",
            task = "Evinizi anlatın. Kaç oda var?",
            usefulWords = listOf("Apartman", "Oda var", "Mutfak", "Rahattır"),
            sentenceModel = listOf(
                BilingualSentence("I live in an apartment.", "Bir apartmanda yaşıyorum."),
                BilingualSentence("There are three rooms in my house.", "Evimde üç oda var."),
                BilingualSentence("My room is comfortable.", "Odam rahattır."),
                BilingualSentence("Thank you.", "Teşekkür ederim.")
            ),
            grammarFocus = "Var / Yok", wordCountGuide = "50-80 kelime"
        ),
        WritingTopic(
            id = "w10", level = "A2", domain = WritingDomain.ACADEMIC,
            titleTr = "Üniversite Hayatı", titleEn = "University Life",
            task = "Üniversite hayatınızdan bahsedin.",
            usefulWords = listOf("Öğrenciyim", "Sınavlarım", "Kütüphane", "Yoğundur"),
            sentenceModel = listOf(
                BilingualSentence("I am a university student.", "Ben bir university öğrencisiyim."),
                BilingualSentence("I study at a university in Turkey.", "Türkiye’de bir üniversitede okuyorum."),
                BilingualSentence("University life is busy.", "Üniversite hayatı yoğundur."),
                BilingualSentence("Thank you.", "Teşekkür ederim.")
            ),
            grammarFocus = "Şimdiki Zaman", wordCountGuide = "70-120 kelime"
        ),
        // B1 Level
        WritingTopic(
            id = "w11", level = "B1", domain = WritingDomain.TECHNOLOGY,
            titleTr = "Teknolojinin Avantajları", titleEn = "Advantages of Technology",
            task = "Teknolojinin avantajlarını ve dezavantajlarını anlatın.",
            usefulWords = listOf("Parçasıdır", "Hemen her", "Yardımcı olur", "Ancak", "Dengeli"),
            sentenceModel = listOf(
                BilingualSentence("Technology is an important part of modern life.", "Teknoloji modern hayatın önemli bir parçasıdır."),
                BilingualSentence("It helps communication, education, and work.", "İletişim, eğitim ve işe yardımcı olur."),
                BilingualSentence("However, technology also has disadvantages.", "Ancak teknolojinin bazı dezavantajları da vardır."),
                BilingualSentence("Therefore, we should use technology in a balanced way.", "Bu yüzden teknolojiyi dengeli kullanmalıyız.")
            ),
            grammarFocus = "Görüş Bildirme", wordCountGuide = "100-150 kelime"
        ),
        WritingTopic(
            id = "w12", level = "B1", domain = WritingDomain.TRANSPORT,
            titleTr = "Şehirde Ulaşım", titleEn = "City Transportation",
            task = "Şehir içi ulaşımı değerlendirin.",
            usefulWords = listOf("Toplu taşıma", "Genellikle", "Ancak", "Kirliliği"),
            sentenceModel = listOf(
                BilingualSentence("Transportation is very important in big cities.", "Büyük şehirlerde ulaşım çok önemlidir."),
                BilingualSentence("Public transportation is usually cheaper and more practical.", "Toplu taşıma genellikle daha ucuz ve pratiktir."),
                BilingualSentence("Public transport is also better for the environment.", "Toplu taşıma çevre için de daha iyidir.")
            ),
            grammarFocus = "Kıyaslama", wordCountGuide = "100-150 kelime"
        ),
        WritingTopic(
            id = "w13", level = "B1", domain = WritingDomain.SOCIETY,
            titleTr = "Kültürel Farklılıklar", titleEn = "Cultural Differences",
            task = "Kültürel farklılıklar üzerine yazın.",
            usefulWords = listOf("Farklılıklar", "Gelenekler", "Yaşam tarzı", "Anlayış"),
            sentenceModel = listOf(
                BilingualSentence("Cultural differences exist between countries.", "Ülkeler arasında kültürel farklılıklar vardır."),
                BilingualSentence("However, they also help people learn new things.", "Ancak aynı zamanda insanlara yeni şeyler öğretir."),
                BilingualSentence("We should try to understand different cultures.", "Farklı kültürleri anlamaya çalışmalıyız.")
            ),
            grammarFocus = "Soyut Kavramlar", wordCountGuide = "100-150 kelime"
        ),
        WritingTopic(
            id = "w14", level = "B1", domain = WritingDomain.CAREER,
            titleTr = "Gelecek Planları", titleEn = "Future Plans",
            task = "Gelecek planlarınızdan bahsedin.",
            usefulWords = listOf("Gelecek", "Mezuniyet", "Geliştirmek", "Ummak"),
            sentenceModel = listOf(
                BilingualSentence("Everyone has future plans in life.", "Herkesin hayatta gelecek planları vardır."),
                BilingualSentence("After graduation, I want to find a good job.", "Mezun olduktan sonra iyi bir iş bulmak istiyorum."),
                BilingualSentence("Therefore, I am working hard now.", "Bu yüzden şimdi çok çalışıyorum.")
            ),
            grammarFocus = "Gelecek Zaman", wordCountGuide = "80-120 kelime"
        ),
        WritingTopic(
            id = "w15", level = "B1", domain = WritingDomain.SOCIAL,
            titleTr = "İletişim ve İnsanlar", titleEn = "Communication and People",
            task = "İletişimin önemini tartışın.",
            usefulWords = listOf("Güçlendirir", "Anlaşılma", "Açık", "Saygılı"),
            sentenceModel = listOf(
                BilingualSentence("Communication is very important in human life.", "İletişim insan hayatında çok önemlidir."),
                BilingualSentence("Good communication helps people understand each other.", "İyi iletişim insanların birbirini anlamasını sağlar."),
                BilingualSentence("Effective communication is necessary in all areas of life.", "Etkili iletişim hayatın her alanında gereklidir.")
            ),
            grammarFocus = "Soyut Kavramlar", wordCountGuide = "100-150 kelime"
        ),
        WritingTopic(
            id = "w16", level = "B1", domain = WritingDomain.ACADEMIC,
            titleTr = "Eğitim Sistemi", titleEn = "Education System",
            task = "Eğitimin önemini anlatın.",
            usefulWords = listOf("Geliştirmesine", "Başarı", "Düzenli", "Hazırlar"),
            sentenceModel = listOf(
                BilingualSentence("Education is one of the most important parts of life.", "Eğitim hayatın en önemli parçalarından biridir."),
                BilingualSentence("It helps people develop knowledge and skills.", "İnsanların bilgi ve becerilerini geliştirmesine yardımcı olur."),
                BilingualSentence("Therefore, education should be valued by everyone.", "Bu yüzden eğitim herkes tarafından değerli görülmelidir.")
            ),
            grammarFocus = "Gereklilik", wordCountGuide = "100-150 kelime"
        ),
        WritingTopic(
            id = "w17", level = "B1", domain = WritingDomain.HEALTH,
            titleTr = "Sağlıklı Yaşam", titleEn = "Healthy Lifestyle",
            task = "Sağlıklı yaşam için neler gereklidir?",
            usefulWords = listOf("Beslenmelidir", "Vücudun", "Olumsuz", "Dikkat"),
            sentenceModel = listOf(
                BilingualSentence("Health is very important for a good life.", "Sağlık iyi bir yaşam için çok önemlidir."),
                BilingualSentence("People should eat healthy and balanced food.", "İnsanlar sağlıklı ve dengeli beslenmelidir."),
                BilingualSentence("Therefore, everyone should take care of their health.", "Bu yüzden herkes sağlığına dikkat etmelidir.")
            ),
            grammarFocus = "Gereklilik Kipi", wordCountGuide = "100-150 kelime"
        ),
        WritingTopic(
            id = "w18", level = "B1", domain = WritingDomain.CAREER,
            titleTr = "İş Hayatı", titleEn = "Work Life",
            task = "İş hayatının önemi.",
            usefulWords = listOf("Yetişkinler", "Kazanmak", "Meslek", "Tecrübe"),
            sentenceModel = listOf(
                BilingualSentence("Work life is very important for adults.", "İş hayatı yetişkinler için çok önemlidir."),
                BilingualSentence("Hard work is important for success.", "Başarı için çok çalışmak önemlidir."),
                BilingualSentence("In the future, I want a successful career.", "Gelecekte başarılı bir kariyer istiyorum.")
            ),
            grammarFocus = "Geniş Zaman", wordCountGuide = "100-150 kelime"
        ),
        WritingTopic(
            id = "w19", level = "B1", domain = WritingDomain.ENVIRONMENT,
            titleTr = "Çevre ve Doğa", titleEn = "Environment and Nature",
            task = "Çevreyi koruma yolları.",
            usefulWords = listOf("Yaşam için", "Kirlilik", "Ciddi", "Sorumluluk"),
            sentenceModel = listOf(
                BilingualSentence("The environment is very important for life.", "Çevre yaşam için çok önemlidir."),
                BilingualSentence("Pollution is a serious global problem.", "Kirlilik ciddi bir küresel sorundur."),
                BilingualSentence("A clean environment means a healthy future.", "Temiz çevre sağlıklı bir gelecek demektir.")
            ),
            grammarFocus = "Gereklilik", wordCountGuide = "100-150 kelime"
        ),
        WritingTopic(
            id = "w20", level = "B1", domain = WritingDomain.SOCIETY,
            titleTr = "Türkiye’de Yaşam", titleEn = "Life in Turkey",
            task = "Türkiye'deki yaşamınız.",
            usefulWords = listOf("Alışmak", "Misafirperverlik", "Deneyim", "Uyum"),
            sentenceModel = listOf(
                BilingualSentence("When I came to Turkey, the first few months were a bit difficult.", "Türkiye'ye geldiğimde ilk aylar biraz zordu."),
                BilingualSentence("But I got used to it over time.", "Ama zamanla alıştım."),
                BilingualSentence("I love Turkish food and culture very much.", "Türk yemeklerini ve kültürünü çok seviyorum.")
            ),
            grammarFocus = "Geçmiş Zaman", wordCountGuide = "100-150 kelime"
        )
    )

    fun getSubCategoriesForCategory(categoryId: String): List<String> {
        // This is for the survival categories, not writing domains
        return listOf("Basics", "Help", "Time", "Language", "Social", "Problems") 
    }
}
