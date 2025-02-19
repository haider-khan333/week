package com.android.weeknumber

class Utils {
    private val motivationsForMen = listOf(
        "🔥 Legacy – Build something that outlives you.",
        "🎯 Discipline Over Motivation – Winners don’t rely on motivation; they rely on habits.",
        "💪 Physical Strength – A strong body leads to a strong mind. Train daily.",
        "🧠 Mental Resilience – Life is tough, but you are tougher.",
        "💰 Financial Freedom – Money buys time, security, and choices.",
        "🏋️ Self-Reliance – Be the man who doesn’t need rescuing.",
        "⚔️ Respect Over Attention – Seek to be respected, not just noticed.",
        "📚 Growth Mindset – Always be learning, always be evolving.",
        "🦾 Stoicism – Control what you can, endure what you must.",
        "⏳ Time is Limited – Every day wasted is a day you don’t get back.",
        "👨‍👩‍👧‍👦 Protect & Provide – Be the rock for your family and community.",
        "🏆 Mastery – Be exceptional at something; don’t settle for mediocrity.",
        "🏗️ Hard Work Wins – Grind now, enjoy the rewards later.",
        "🚫 No Excuses – Take ownership of everything in your life.",
        "🛡️ Respect Your Name – Build a reputation that your future self will thank you for.",
        "🤝 Brotherhood – Surround yourself with strong, driven men who push you higher.",
        "🦅 Attract, Don’t Chase – Become so valuable that success, women, and opportunities come to you.",
        "😈 Face Your Fears – Confidence is built by conquering what scares you.",
        "👑 Be a Leader – Inspire others through your actions, not just words.",
        "🎢 Enjoy the Process – The journey makes the man, not just the destination.",
        "🦍 Strength is Earned – Nothing great comes easy. Keep pushing.",
        "🌎 Conquer Yourself – Before you change the world, master your mind.",
        "🔑 Self-Control is Power – The man who controls himself controls everything.",
        "🏹 Focus Like a Hunter – Eliminate distractions and hit your targets.",
        "🎭 No One Cares – Work harder, prove yourself through results, not words.",
        "💼 Build Your Empire – Work like a king, think like a warrior.",
        "⏰ Your Time is Now – Stop waiting. Take action today.",
        "🦁 Dominate Every Arena – Wherever you go, make an impact.",
        "💎 Be Valuable – Instead of chasing opportunities, become so skilled that they chase you.",
        "🚀 Work in Silence – Let your success make the noise.",
        "🔥 Burn the Excuses – No more complaining. Get up and make things happen.",
        "💡 Learn, Adapt, Dominate – The strongest men evolve with challenges.",
        "🌊 Stay Unshaken – Be like the ocean—calm but powerful when needed.",
        "🚴‍♂️ No Comfort Zone – Growth happens when you're uncomfortable.",
        "🏋️‍♂️ Train Your Body & Mind – A weak man is a liability to himself.",
        "💀 Face Your Demons – The things you fear hold the keys to your next level.",
        "🌱 Start Now, Improve Later – The best time to begin was yesterday; the second-best time is now.",
        "💥 Keep Moving Forward – Never let failure define you; let it refine you.",
        "🛠️ Build Your Own Path – Stop following. Carve your own way.",
        "🎖️ Be a Man of Honor – Live by principles, not opinions."
    )

    fun giveRandomMotivation(): String {
        return motivationsForMen.random()
    }
}