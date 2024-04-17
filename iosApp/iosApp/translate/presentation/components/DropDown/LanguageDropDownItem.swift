//
//  LanguageDropDown.swift
//  iosApp
//
//  Created by Jose Filho on 17/04/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LanguageDropDownItem: View {
    var language: UiLanguage
    var onClick: () -> Void
    var body: some View {
        Button(action: onClick) {
            HStack {
                if let image = UIImage(
                    named: language.imageName.lowercased()
                ) {
                    Image(uiImage: image)
                        .resizable()
                        .frame(width: 40, height: 40)
                        .padding(.trailing, 5)
                    
                    Text(language.language.langName)
                        .foregroundColor(.textBlack)
                }
            }
        }
    }
}

#Preview {
    LanguageDropDownItem(
        language: UiLanguage(language: .german, imageName: "german"), onClick: {}
    )
}
