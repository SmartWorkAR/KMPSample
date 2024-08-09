import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    
    init() {
        MainViewController().doInitKoin()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
