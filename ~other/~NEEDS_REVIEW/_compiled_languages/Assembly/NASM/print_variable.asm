; ==========================================================
; /usr/local/bin/nasm -f macho64 print_variable.asm && ld -macosx_version_min 10.7.0 -lSystem -o print_variable print_variable.o && ./print_variable

; ==========================================================
SYSCALL_WRITE equ 0x2000004
SYSCALL_EXIT  equ 0x2000001
STDOUT equ 0x0000001
NEW_LINE equ 0x0a ; 0x0a is same as 10
NULL_CHARACTER equ 0

; ==========================================================
section .bss ; to reserve memory for future use

; ==========================================================
section .data

myVariable: db NEW_LINE,NEW_LINE,"hello", NEW_LINE, NULL_CHARACTER
.len: equ $ - myVariable

; ==========================================================
section .text
global start

start:
  mov rax, SYSCALL_WRITE
  mov rdi, STDOUT
  mov rsi, myVariable
  mov rdx, myVariable.len
  syscall

  mov rax, SYSCALL_EXIT
  mov rdi, 0
  syscall

; ==========================================================
