//
//  ProgressButton.swift
//  iosApp
//
//  Created by Jose Filho on 20/04/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ProgressButton: View {
    var text: String
    var isLoading: Bool
    var onClick: () -> Void
    
    var body: some View {
        Button(
            action: {
                if !isLoading {
                    onClick()
                }
            }
        ) {
            if isLoading {
                ProgressView()
                    .animation(.easeInOut, value: isLoading)
                    .padding()
                    .background(Color.primaryColor)
                    .cornerRadius(100)
                    .progressViewStyle(CircularProgressViewStyle(tint: .white))
                
            } else {
                Text(text.uppercased())
                    .animation(.easeInOut, value: isLoading)
                    .padding(.horizontal)
                    .padding(.vertical, 5)
                    .font(.body.weight(.bold))
                    .background(Color.primaryColor)
                    .foregroundColor(Color.onPrimary)
                    .cornerRadius(100)
            }
        }
    }
}
