; ========================================================== Notes
; /usr/local/bin/nasm -f macho64 print_struc.asm && ld -macosx_version_min 10.7.0 -lSystem -o print_struc print_struc.o && ./print_struc

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

mySpace: db NEW_LINE, NEW_LINE
.len: equ $ - mySpace

myVariable: db NEW_LINE,NEW_LINE,"hello", NEW_LINE, NULL_CHARACTER
.len: equ $ - myVariable


struc Person
  firstName resb 50
  lastName resb 50
  address resb 50
  city resb 50
  country resb 50
  num1 resb 64
  num2 resb 64
endstruc

brian istruc Person
  at firstName, dd "brian", NEW_LINE
  at lastName, dd "spinos", NEW_LINE
  at address, dd "123 foobar st", NEW_LINE
  at city, dd "phoenix", NEW_LINE
  at country, dd "usa", NEW_LINE
  at num1, dd 123
  at num2, dd 456
iend

; ==========================================================
section .text
global start

start:
  call printSpace
  call printMyVariable
  call printFirstName
  call printLastName
  call exitProgram

printSpace:
  mov rax, SYSCALL_WRITE
  mov rdi, STDOUT
  mov rsi, mySpace
  mov rdx, mySpace.len
  syscall
  ret

printMyVariable:
  mov rax, SYSCALL_WRITE
  mov rdi, STDOUT
  mov rsi, myVariable
  mov rdx, myVariable.len
  syscall
  ret

printFirstName:
  mov rax, SYSCALL_WRITE
  mov rdi, STDOUT
  mov rsi, brian + firstName
  mov rdx, 50
  syscall
  ret

printLastName:
  mov rax, SYSCALL_WRITE
  mov rdi, STDOUT
  mov rsi, brian + lastName
  mov rdx, 50
  syscall
  ret

exitProgram:
  mov rax, SYSCALL_EXIT
  mov rdi, 0
  syscall
  ret


; ==========================================================
