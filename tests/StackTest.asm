// C_PUSH constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_PUSH constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_ARITHMETIC eq
@SP
M=M-1
A=M
D=M
A=A-1
D=M-D
M=-1
@d6412ace-7655-4864-b931-aa0a9d7bfabd
D;JEQ
@SP
A=M-1
M=0
(d6412ace-7655-4864-b931-aa0a9d7bfabd)

// C_PUSH constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_PUSH constant 16
@16
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_ARITHMETIC eq
@SP
M=M-1
A=M
D=M
A=A-1
D=M-D
M=-1
@a1904437-2bf0-43df-8fb3-4ed42f4dd6d4
D;JEQ
@SP
A=M-1
M=0
(a1904437-2bf0-43df-8fb3-4ed42f4dd6d4)

// C_PUSH constant 16
@16
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_PUSH constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_ARITHMETIC eq
@SP
M=M-1
A=M
D=M
A=A-1
D=M-D
M=-1
@c89738c0-c164-43aa-a57b-8d3aacf999cd
D;JEQ
@SP
A=M-1
M=0
(c89738c0-c164-43aa-a57b-8d3aacf999cd)

// C_PUSH constant 892
@892
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_PUSH constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_ARITHMETIC lt
@SP
M=M-1
A=M
D=M
A=A-1
D=M-D
M=-1
@c7e65036-abde-4f3a-9160-92080e22f742
D;JLT
@SP
A=M-1
M=0
(c7e65036-abde-4f3a-9160-92080e22f742)

// C_PUSH constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_PUSH constant 892
@892
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_ARITHMETIC lt
@SP
M=M-1
A=M
D=M
A=A-1
D=M-D
M=-1
@9527e467-3b55-440f-a52e-5af865a41595
D;JLT
@SP
A=M-1
M=0
(9527e467-3b55-440f-a52e-5af865a41595)

// C_PUSH constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_PUSH constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_ARITHMETIC lt
@SP
M=M-1
A=M
D=M
A=A-1
D=M-D
M=-1
@2ffa221b-03f4-4aac-bffc-cdbbd76bbea9
D;JLT
@SP
A=M-1
M=0
(2ffa221b-03f4-4aac-bffc-cdbbd76bbea9)

// C_PUSH constant 32767
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_PUSH constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_ARITHMETIC gt
@SP
M=M-1
A=M
D=M
A=A-1
D=M-D
M=-1
@643559fd-ddbc-41f3-8e13-af4a63ad4552
D;JGT
@SP
A=M-1
M=0
(643559fd-ddbc-41f3-8e13-af4a63ad4552)

// C_PUSH constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_PUSH constant 32767
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_ARITHMETIC gt
@SP
M=M-1
A=M
D=M
A=A-1
D=M-D
M=-1
@651a69c9-8623-4296-bd4e-b487e574f565
D;JGT
@SP
A=M-1
M=0
(651a69c9-8623-4296-bd4e-b487e574f565)

// C_PUSH constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_PUSH constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_ARITHMETIC gt
@SP
M=M-1
A=M
D=M
A=A-1
D=M-D
M=-1
@f04e0d2f-c97f-49b6-8c77-d194e04ecfd2
D;JGT
@SP
A=M-1
M=0
(f04e0d2f-c97f-49b6-8c77-d194e04ecfd2)

// C_PUSH constant 57
@57
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_PUSH constant 31
@31
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_PUSH constant 53
@53
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_ARITHMETIC add
@SP
M=M-1
A=M
D=M
A=A-1
M=M+D

// C_PUSH constant 112
@112
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_ARITHMETIC sub
@SP
M=M-1
A=M
D=M
A=A-1
M=M-D

// C_ARITHMETIC neg
@SP
A=M-1
M=-M

// C_ARITHMETIC and
@SP
M=M-1
A=M
D=M
A=A-1
M=D&M

// C_PUSH constant 82
@82
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_ARITHMETIC or
@SP
M=M-1
A=M
D=M
A=A-1
M=D|M

// C_ARITHMETIC not
@SP
A=M-1
M=!M

