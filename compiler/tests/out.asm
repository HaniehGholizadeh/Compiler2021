.data
	newLine: 	.asciiz 	"\n"
	bool_0: 	.asciiz 	"false"
	bool_1: 	.asciiz 	"true"
	zeroDouble: 	.double 	0.0
	mainScope.a:	.word	0
	mainScope.b:	.word	0
	mainScope.i:	.word	0
	LoopStmt_1Scope_StringLiteral0:	.asciiz	"Please enter the #"
	LoopStmt_1Scope_StringLiteral1:	.asciiz	" number:"
	LoopStmt_1Scope_StringLiteral2:	.asciiz	"Sum of "
	LoopStmt_1Scope_StringLiteral3:	.asciiz	" items is: "
.text
	ldc1	$f0, zeroDouble
	jal	main
PrintBool:
	beq	$a0, 0, Print_Bool0
	la	$v1, bool_1
	b	Print_Bool1
Print_Bool0:
	la	$v1, bool_0
Print_Bool1:
	jr	$ra
main:
	addi	$sp, $sp, -16
	sw	$a0, 0($sp)
	sw	$a1, 4($sp)
	sw	$a2, 8($sp)
	sw	$a3, 12($sp)
	li	$v1, 0
	sw	$v1  mainScope.b						# End assign
	li	$v1, 1
	sw	$v1  mainScope.i						# End assign
mainScope_LoopStmt_1_start:
	li	$v1, 1
	beq	$v1, 0, mainScope_LoopStmt_1_end
	la	$v1, LoopStmt_1Scope_StringLiteral0
	li	$v0, 4
	move	$a0, $v1
	syscall
	lw	$v1, mainScope.i
	li	$v0, 1
	add	$a0, $v1, $zero
	syscall
	la	$v1, LoopStmt_1Scope_StringLiteral1
	li	$v0, 4
	move	$a0, $v1
	syscall
	li	$v0, 4
	la	$a0, newLine
	syscall
	addi	$sp, $sp, -16
	sw	$a0, 0($sp)
	sw	$a1, 4($sp)
	sw	$a2, 8($sp)
	sw	$a3, 12($sp)
	li	$v0, 5
	syscall
	move	$v1, $v0
	lw	$a0, 0($sp)
	lw	$a1, 4($sp)
	lw	$a2, 8($sp)
	lw	$a3, 12($sp)
	addi	$sp, $sp, 16
	sw	$v1  mainScope.a						# End assign
	addi	$sp, $sp, -24
	sw	$s0, 0($sp)
	sw	$s1, 4($sp)
	sw	$s2, 8($sp)
	sw	$s3, 12($sp)
	sw	$s4, 16($sp)
	sw	$s5, 20($sp)
	lw	$v1, mainScope.a
	move	$s0, $v1
	li	$v1, 0
	move	$s2, $v1
	slt	$v1, $s0, $s2
	lw	$s0, 0($sp)
	lw	$s1, 4($sp)
	lw	$s2, 8($sp)
	lw	$s3, 12($sp)
	lw	$s4, 16($sp)
	lw	$s5, 20($sp)
	addi	$sp, $sp, 24
	beq	$v1, 0, LoopStmt_1Scope_IfStmt_1
	b	mainScope_LoopStmt_1_end
LoopStmt_1Scope_IfStmt_1:
	addi	$sp, $sp, -24
	sw	$s0, 0($sp)
	sw	$s1, 4($sp)
	sw	$s2, 8($sp)
	sw	$s3, 12($sp)
	sw	$s4, 16($sp)
	sw	$s5, 20($sp)
	lw	$v1, mainScope.b
	move	$s0, $v1
	lw	$v1, mainScope.a
	move	$s2, $v1
	add	$v1, $s0, $s2
	lw	$s0, 0($sp)
	lw	$s1, 4($sp)
	lw	$s2, 8($sp)
	lw	$s3, 12($sp)
	lw	$s4, 16($sp)
	lw	$s5, 20($sp)
	addi	$sp, $sp, 24
	sw	$v1  mainScope.b						# End assign
mainScope_LoopStmt_1_update:
	addi	$sp, $sp, -24
	sw	$s0, 0($sp)
	sw	$s1, 4($sp)
	sw	$s2, 8($sp)
	sw	$s3, 12($sp)
	sw	$s4, 16($sp)
	sw	$s5, 20($sp)
	lw	$v1, mainScope.i
	move	$s0, $v1
	li	$v1, 1
	move	$s2, $v1
	add	$v1, $s0, $s2
	lw	$s0, 0($sp)
	lw	$s1, 4($sp)
	lw	$s2, 8($sp)
	lw	$s3, 12($sp)
	lw	$s4, 16($sp)
	lw	$s5, 20($sp)
	addi	$sp, $sp, 24
	sw	$v1  mainScope.i						# End assign
	b	mainScope_LoopStmt_1_start
mainScope_LoopStmt_1_end:
	la	$v1, LoopStmt_1Scope_StringLiteral2
	li	$v0, 4
	move	$a0, $v1
	syscall
	lw	$v1, mainScope.i
	li	$v0, 1
	add	$a0, $v1, $zero
	syscall
	la	$v1, LoopStmt_1Scope_StringLiteral3
	li	$v0, 4
	move	$a0, $v1
	syscall
	lw	$v1, mainScope.b
	li	$v0, 1
	add	$a0, $v1, $zero
	syscall
	li	$v0, 4
	la	$a0, newLine
	syscall
	# This line is going to signal end of program.
	li	$v0, 10
	syscall
