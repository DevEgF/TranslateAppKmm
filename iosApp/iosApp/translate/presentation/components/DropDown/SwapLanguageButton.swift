//
//  SwapLanguageButton.swift
//  iosApp
//
//  Created by Jose Filho on 17/04/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SwapLanguageButton: View {
    var onClick: () -> Void
    
    var body: some View {
        Button(action: onClick) {
            Image(uiImage: UIImage(named: "swap_languages")!)
                .padding()
                .background(Color.primaryColor)
                .clipShape(Circle())
        }
    }
}

#Preview {
    SwapLanguageButton(
        onClick: {}
    )
}
