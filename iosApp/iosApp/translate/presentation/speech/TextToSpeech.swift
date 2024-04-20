//
//  TextToSpeech.swift
//  iosApp
//
//  Created by Jose Filho on 20/04/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import AVFoundation

struct TextToSpeech {
    
    private let synthesizer = AVSpeechSynthesizer()
    
    func speak(text: String, language: String) {
        let utterance = AVSpeechUtterance(string: text)
        
        utterance.voice = AVSpeechSynthesisVoice(language: language)
        utterance.volume = 1
        
        synthesizer.speak(utterance)
    }
}
