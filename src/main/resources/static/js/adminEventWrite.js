    const fileInput = document.querySelector('#file');
    const imgViewBox = document.querySelector('.img-box');
    
    function fileUpload(event) {
        
        const file = event.target.files[0];
        //console.log('file', file);
        // 파일 크기 유효성 검사 
        // 1024 * 1024  * 5 =  (5MB) 이하만 허용 
        if(file.size >= 104,857,600) { 
            alert('첨부 파일은 100MB 이하만 가능 합니다');
            event.target.value = "";
            return; 
        }
        
         // 파일 타입 유효성 검사 
        const validFileTypes = ['imgage/jpg', 'image/jpeg', 'image/png', 'image/gif'];
        if(!validFileTypes.includes(file.type))   {
            alert('유효한 파일 타입이 아닙니다.(jpg, jpeg, png, gif만 허용');
            return; 
        } 
        
         // 파일 미리보기 기능 
        const reader = new FileReader();
        reader.readAsDataURL(file); // Base64 인코딩 바이트 단위 데이터를 -- 64개 문자로 표현하는 인코딩 방식
        reader.onload = function (e) {
            console.log('Base64', e.target.result);
            imgViewBox.innerHTML = `<img src="${e.target.result}" alt="Upload Image">`;
            // 글 저장시에 로컬스토리지에 바이너리 데이터를 -- 64 인코딩 저장 
            imageData = reader.result;
        }
        
         fileInput.addEventListener('change', fileUpload);
    }