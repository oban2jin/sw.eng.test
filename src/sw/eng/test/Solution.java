package sw.eng.test;


class Solution {
static int N = 4;
// 솔루션 저장배열
static int[] sol = new int[N];
// 현재 사용중인 숫자를 표시하는 벡터
static int[] perm = new int[N];

public static void main(String args[]) {
	backtrack(0);
}

public static void backtrack(int n) {
	// 솔루션이라면
	if(isSolution(n)) {
	// 솔루션을 처리
	processSolution();
	// 메소드를 종료하여 되돌리기 실행
		return;
	}
	for(int i = 0; i < N; i++) {
		// 현재 사용중인 숫자가 아니라면
		if(perm[i] == 0) {
		// 솔루션 저장
		sol[n] = i + 1;
		// 벡터에 (i + 1) 번호가 사용 중 임을 표시
		perm[i] = 1;
		// 재귀호출
		backtrack(n + 1);
		// 벡터에 사용 중 표시 해제
		perm[i] = 0;
		}
	}
}
// 솔루션 인지를 판단하는 메소드
public static boolean isSolution(int n) {
	return (n == N);
}
// 솔루션 처리 메소드
public static void processSolution() {
	for(int i = 0; i < N; i++) {
		System.out.printf("%d ", sol[i]);
	}
		System.out.println();
	}
}