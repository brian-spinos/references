;
; NASM 64bit
;
; filename: foo.asm
;
; ============================ How to run the code
; # Generate object file from assembly:
; nasm -f macho64 -o foo.o foo.asm
;
; # Link object file:
; ld foo.o -o foo
;
; # Run executable:
; ./foo




;
; ============================ REGISTERS
; rax - What to do (exit, read, write)
; rdi - Where to write to (stdout, ...)
; rsi - The data
; rdx - Data length
;


;
; To make our life easy, lets define some variables
;
%define SYSCALL_WRITE 0x2000004
%define SYSCALL_EXIT  0x2000001
%define STDOUT        1


; global start ; This call could be here also? where is the right place?


;
; ============================ Data section (variables)
;

section .data

msg1: db "Hello, world", 10
.len: equ $ - msg1

msg2: db "Hello, world (again)", 10
.len: equ $ - msg2

msg3: db "Hello, world (once more)", 10
.len: equ $ - msg3

; to use \n, use backticks
msg4 db `Hello, world (one last time)\n\n`
; get the length of the string ; "$" means "here"
msg4_len equ $ - msg4


;
; ============================ BSS section (constants?)
;


section .bss
; this section is for constants?

;
; ============================ Text section (code)
;


section .text

global start ; The main function name


;
; The main function (it can be called anything)
;
start:
    mov rax, SYSCALL_WRITE
    mov rdi, STDOUT
    mov rsi, msg1
    mov rdx, msg1.len
    syscall

    call myFunc2
    call myFunc3
    call myFunc4


    ;
    ; Exit from main function, a must
    ;
    mov rax, SYSCALL_EXIT
    mov rdi, 0
    syscall


myFunc2:
    mov rax, SYSCALL_WRITE
    mov rdi, STDOUT
    mov rsi, msg2
    mov rdx, msg2.len
    syscall
    ret ; Return from function, a must

myFunc3:
    mov rax, SYSCALL_WRITE
    mov rdi, STDOUT
    mov rsi, msg3
    mov rdx, msg3.len
    syscall
    ret ; Return from function, a must

myFunc4:
    mov rax, SYSCALL_WRITE
    mov rdi, STDOUT
    mov rsi, msg4
    mov rdx, msg4_len
    syscall
    ret ; Return from function, a must



;
; ============================ Output
;

; Hello, world
; Hello, world (again)
; Hello, world (once more)
; Hello, world (one last time)



;
; ============================ End
;
