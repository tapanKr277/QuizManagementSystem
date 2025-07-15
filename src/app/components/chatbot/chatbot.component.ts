import { Component, ViewChild, ElementRef, AfterViewChecked } from '@angular/core';
import { AiService } from '../../service/ai-service/ai.service';

@Component({
  selector: 'app-chatbot',
  templateUrl: './chatbot.component.html',
  styleUrls: ['./chatbot.component.css']
})
export class ChatbotComponent implements AfterViewChecked {

  @ViewChild('messagesContainer') messagesContainer: ElementRef | undefined;
  userMessage: string = '';
  isChatboxOpen = false;
  isLoading = false;
  messages: { sender: string; text: string }[] = [];


  constructor(private aiService: AiService) {
    
  }
  sendMessage(): void {
    this.isLoading = true;
    const messgae = this.userMessage.trim();
    this.userMessage = '';
    if (messgae) {
      this.messages.push({ sender: 'user', text: messgae });
      // Simulating bot response
      this.aiService.chatWithBot(messgae).subscribe(res=>{
      this.messages.push({ sender: 'bot', text: res["success"]  });
      this.isLoading = false;
      }, error=>{
        console.log(error);
        this.messages.push({ sender: 'bot', text: "Something went worng!"});
        this.isLoading = false;
        this.userMessage = '';
      })
      
    }
  }

  toggleChatbox() {
    this.isChatboxOpen = !this.isChatboxOpen;
  }

  closeChatbox() {
    this.isChatboxOpen = false;
  }

  // Scroll to the bottom after the view is checked (i.e., after new messages are rendered)
  ngAfterViewChecked(): void {
    this.scrollToBottom();
  }

  private scrollToBottom(): void {
    if (this.messagesContainer) {
      const scrollHeight = this.messagesContainer.nativeElement.scrollHeight;
      const clientHeight = this.messagesContainer.nativeElement.clientHeight;
      this.messagesContainer.nativeElement.scrollTop = scrollHeight - clientHeight;
    }
  }
}
``
