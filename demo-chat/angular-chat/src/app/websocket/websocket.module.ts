import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LayoutComponent } from './layout.component';
import { ChatComponent } from './chat.component';
import { FormsModule } from '@angular/forms';
import { WebsocketRoutingModule } from './websocket-routing.module';



@NgModule({
  declarations: [
    LayoutComponent,
    ChatComponent
  ],
  imports: [
    CommonModule,
    WebsocketRoutingModule,
    FormsModule
  ]
})
export class WebsocketModule { }
