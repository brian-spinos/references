import React, { useEffect, useRef, useState } from 'react';

const ExamplesWidth = () => {
	const ref = useRef(null);

	const [width, setWidth] = useState(null);
	const [windowWidth, setWindowWidth] = useState(0);

	useEffect(() => {
		setWidth(ref.current.offsetWidth);

		const handleResize = () => {
			setWindowWidth(window.innerWidth);
			// setWidth(ref.current.offsetWidth);
		};

		return window.addEventListener('resize', handleResize);
	}, [windowWidth]);

	return (
		<div className="bg-green-100 w-full" ref={ref}>
			<p>ExamplesWidth</p>
			<p>width: {width}</p>
		</div>
	);
};

export default ExamplesWidth;
// https://bobbyhadz.com/blog/react-get-parent-width-height
